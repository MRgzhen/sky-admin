package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.util.AssertUtil;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.IsDefaultType;
import com.yanyu.sky.sys.bean.po.Dept;
import com.yanyu.sky.sys.bean.po.User;
import com.yanyu.sky.sys.bean.vo.dept.DeptNodeVo;
import com.yanyu.sky.sys.bean.vo.dept.DeptSaveVo;
import com.yanyu.sky.sys.bean.vo.dept.DeptSearchVo;
import com.yanyu.sky.sys.dao.DeptMapper;
import com.yanyu.sky.sys.dao.UserMapper;
import com.yanyu.sky.sys.service.IDeptService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门 业务实现类
 * @author yanyu
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<DeptNodeVo> listDeptPage(DeptSearchVo vo) {
        IPage<DeptNodeVo> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        page = baseMapper.listDeptPage(page, Wrappers.<Dept>lambdaQuery()
                .eq(Dept::getParentId, SysConstant.DEFAULT_PARENT_ID).eq(Dept::getIsDel, IsDefaultType.NO).orderByAsc(Dept::getSort));
        List<Dept> depts = baseMapper.selectList(Wrappers.<Dept>lambdaQuery().ne(Dept::getParentId, SysConstant.DEFAULT_PARENT_ID).orderByAsc(Dept::getSort));
        initTree(page.getRecords(), depts);
        return page;
    }

    @Override
    public void add(DeptSaveVo vo) {
        canUpdate(vo.getId(), vo.getDeptName());

//        if(!SysConstant.DEFAULT_PARENT_ID.equals(vo.getParentId())) {
//            Dept parentDept = baseMapper.selectById(vo.getParentId());
//            AssertUtil.isNotNull(parentDept,"查不到父部门");
//        }

        Dept dept = new Dept();
        BeanUtils.copyProperties(vo, dept);
        baseMapper.insert(dept);
    }

    @Override
    public void update(DeptSaveVo vo) {
        canUpdate(vo.getId(), vo.getDeptName());

        Dept dept = new Dept();
        BeanUtils.copyProperties(vo, dept);

        // 判断父节点是否改变，改变->判断当前选中节点是否为该节点下子节点，如果是，则返回失败
        Dept curDept = baseMapper.selectById(vo.getId());
        if(!curDept.getParentId().equals(vo.getParentId())) {
            int result = assertChildNode(vo.getParentId(), vo.getId());
            AssertUtil.isTrue(result<=0,"不能选择该节点及该节点下子节点");
        }
        baseMapper.updateById(dept);
    }

    @Override
    public void updateEnabled(DeptSaveVo vo) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(vo, dept);
        // 禁用，禁用当前节点及其子节点
        if(EnabledType.DISABLED.equals(vo.getEnabled())) {
            updateChild(dept);
        } else {
            baseMapper.updateById(dept);
        }
    }

    @Override
    public void delete(IdsEntity vo) {
        // 查询部门及其子部门是否关联用户
        int userCnt = assertChildDeptOfUser(vo.getIds().iterator().next());
        AssertUtil.isTrue(userCnt<=0,"该部门下关联的有用户，不能删除");
        // 删除部门及其子部门
        deleteChildDept(vo.getIds().iterator().next());
    }

    private void initTree(List<DeptNodeVo> topDepts, List<Dept> depts) {
        // 父类向子类转换
        List<DeptNodeVo> deptNodes = depts.stream().map(dept -> {
            DeptNodeVo deptTree = new DeptNodeVo();
            BeanUtils.copyProperties(dept, deptTree);
            return deptTree;
        }).collect(Collectors.toList());

        // 创建树
        if(CollectionUtils.isNotEmpty(topDepts)) {
            topDepts.stream().forEach(deptNode -> {
                createDeptTree(deptNode, deptNodes);
            });
        }
    }

    private void createDeptTree(DeptNodeVo curDeptNode, List<DeptNodeVo> allMeptNodes) {
        List<DeptNodeVo> nextDeptNodes = allMeptNodes.stream()
                .filter(menuNode -> StringUtils.equals(curDeptNode.getId(),menuNode.getParentId()))
                .sorted().collect(Collectors.toList());
        nextDeptNodes.stream().forEach(deptNode -> {
            createDeptTree(deptNode, allMeptNodes);
        });
        if(CollectionUtils.isNotEmpty(nextDeptNodes)) {
            curDeptNode.setChildren(nextDeptNodes);
        }
    }

    /**
     * 判断parentId是否是id的节点或id的子节点
     * @param parentId
     * @param id
     * @return
     */
    private int assertChildNode(String parentId,String id) {
        if(StringUtils.equals(parentId, id)) {
            return 1;
        }

        List<Dept> childDepts = baseMapper.selectList(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId,id));
        int result = 0;
        for(int i = 0;i < childDepts.size();i++) {
            result = assertChildNode(parentId, childDepts.get(i).getId());
            if(result > 0) {
                return result;
            }
        }
        return 0;
    }

    /**
     * 更新当前节点及其字节
     * @param dept
     */
    private void updateChild(Dept dept) {
        baseMapper.updateById(dept);
        List<Dept> childDepts = baseMapper.selectList(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId,dept.getId()));
        childDepts.stream().forEach(childDept -> {
            Dept tmpDept = new Dept();
            tmpDept.setId(childDept.getId());
            tmpDept.setEnabled(dept.getEnabled());
            updateChild(tmpDept);
        });
    }

    /**
     * 判断当前节点及其字节带你是否关联用户
     * @param parentId
     * @return
     */
    private int assertChildDeptOfUser(String id) {
        int userCnt = userMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getDeptId, id));
        if(userCnt > 0) {
            return userCnt;
        }

        List<Dept> childDepts = baseMapper.selectList(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId, id));
        int result = 0;
        for(int i = 0;i < childDepts.size();i++) {
            result = assertChildDeptOfUser(childDepts.get(i).getId());
            if(result > 0) {
                return result;
            }
        }
        return result;
    }

    /**
     * 删除当前节点及其字节
     * @param parentId
     */
    private void deleteChildDept(String parentId) {
        baseMapper.deleteById(parentId);
        List<Dept> childDepts = baseMapper.selectList(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId, parentId));
        childDepts.stream().forEach(dept -> {
            deleteChildDept(dept.getId());
        });
    }

    private void canUpdate(String id, String deptName) {
        int deptNameCnt = baseMapper.selectCount(Wrappers.<Dept>lambdaQuery()
                .ne(StringUtils.isNoneBlank(id), Dept::getId, id).eq(Dept::getDeptName, deptName));
        AssertUtil.isTrue(deptNameCnt<=0,"部门编码已存在");
    }
}
