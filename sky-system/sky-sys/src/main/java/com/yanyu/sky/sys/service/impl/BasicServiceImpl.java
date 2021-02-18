package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.LeafType;
import com.yanyu.sky.sys.bean.enums.MenuType;
import com.yanyu.sky.sys.bean.po.*;
import com.yanyu.sky.sys.dao.*;
import com.yanyu.sky.sys.bean.vo.basic.BasicPair;
import com.yanyu.sky.sys.bean.vo.basic.BasicPairNode;
import com.yanyu.sky.sys.service.IBasicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 下拉列表 业务实现类
 * @author yanyu
 */
@Service
@Slf4j
public class BasicServiceImpl implements IBasicService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private DicMapper dicMapper;
    @Autowired
    private DicItemMapper dicItemMapper;

    @Override
    public List<BasicPairNode> listDept() {
        // 查询部门
        List<Dept> depts = deptMapper.selectList(Wrappers.<Dept>lambdaQuery().orderByAsc(Dept::getSort));

        // 转换 Dept->BasicPairNode
        List<BasicPairNode> nodes = depts.stream().map(dept -> {
            return BasicPairNode.builder()
                    .id(dept.getId())
                    .parentId(dept.getParentId())
                    .value(dept.getId())
                    .label(dept.getDeptName())
                    .enabled(dept.getEnabled())
                    .sort(dept.getSort()).build();
        }).collect(Collectors.toList());
        List<String> disabledValus = new ArrayList<>();

        // 创建部门树
        List<BasicPairNode> topNodes = initTree(nodes);

        return topNodes;
    }

    @Override
    public List<BasicPair> listRole() {
        // 查询角色
        List<Role> roles = roleMapper.selectList(Wrappers.<Role>lambdaQuery().orderByDesc(Role::getUpdateTime));

        // 转换 Role->BasicPairNode
        List<BasicPair> basicPairs = roles.parallelStream().map(role -> {
            return BasicPair.builder()
                    .value(role.getId())
                    .label(role.getName())
                    .enabled(role.getEnabled())
                    .build();
        }).collect(Collectors.toList());
        return basicPairs;
    }

    @Override
    public List<BasicPair> listRoleByType(String type) {
        // 根据用户类型查询角色
        List<Role> roles = roleMapper.selectList(Wrappers.<Role>lambdaQuery().eq(Role::getType, type).orderByDesc(Role::getUpdateTime));

        // 转换 Role->BasicPairNode
        List<BasicPair> basicPairs = roles.parallelStream().map(role -> {
            return BasicPair.builder()
                    .value(role.getId())
                    .label(role.getName())
                    .enabled(role.getEnabled())
                    .build();
        }).collect(Collectors.toList());
        return basicPairs;
    }

    @Override
    public List<BasicPairNode> listMenuByType(String type) {
        // 如果类型为空，则查询菜单类型为目录，菜单的数据
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.<Menu>lambdaQuery();
        if(StringUtils.isNotBlank(type)) {
            queryWrapper.eq(Menu::getType, type);
        } else {
            queryWrapper.in(Menu::getType, MenuType.MENU, MenuType.CATALOG);
        }
        queryWrapper.orderByAsc(Menu::getSort);
        List<Menu> menus = menuMapper.selectList(queryWrapper);

        return menus(menus);
    }

    @Override
    public List<BasicPairNode> listMenuWithPerm() {
        // 查询菜单类型为目录，菜单的数据
        List<Menu> menus = menuMapper.selectList(Wrappers.<Menu>lambdaQuery().orderByAsc(Menu::getSort));
        return menus(menus);
    }

    @Override
    public List<BasicPair> listPDic() {
        // 查询顶级数据字典
        List<Dic> dics = dicMapper.selectList(Wrappers.<Dic>lambdaQuery().orderByAsc(Dic::getSort));

        // 转换 Dic->BasicPairNode
        List<BasicPair> basicPairs = dics.parallelStream().map(dic -> {
            return BasicPair.builder()
                    .value(dic.getId())
                    .label(dic.getLabel())
                    .enabled(dic.getEnabled()).build();
        }).collect(Collectors.toList());

        // 默认初始化顶级数据字典
        return basicPairs;
    }

    @Override
    public List<BasicPair> listChildDicByCode(String code) {
        // 根据编码查询数据字典
        List<DicItem> childDics = dicItemMapper.selectList(Wrappers.<DicItem>lambdaQuery()
                .eq(DicItem::getCode, code).orderByDesc(DicItem::getIsDefault).orderByAsc(DicItem::getSort));

        // 转换 Dic->BasicPair
        return childDics.parallelStream().map(childDic ->
                BasicPair.builder()
                        .value(childDic.getValue())
                        .label(childDic.getName())
                        .enabled(childDic.getEnabled())
                        .build()).collect(Collectors.toList());
    }

    private List<BasicPairNode> menus(List<Menu> menus) {
        // 转换 Menu->BasicPairNode
        List<BasicPairNode> nodes = menus.parallelStream().map(menu -> {
            return BasicPairNode.builder()
                    .id(menu.getId())
                    .parentId(menu.getParentId())
                    .label(menu.getName())
                    .value(menu.getId())
                    .type(menu.getType().getValue())
                    .sort(menu.getSort())
                    .enabled(menu.getEnabled())
                    .build();
        }).collect(Collectors.toList());

        // 创建树
        List<BasicPairNode> topNodes = initTree(nodes);

        // 默认初始化顶级目录
        return Arrays.asList(BasicPairNode.builder()
                .id(SysConstant.DEFAULT_PARENT_ID)
                .label(SysConstant.MENU_TOP_NAME)
                .value(SysConstant.DEFAULT_PARENT_ID)
                .type("0")
                .sort(0)
                .enabled(EnabledType.ENABLED)
                .children(topNodes).build());
    }

    private List<BasicPairNode> initTree(List<BasicPairNode> nodes) {
        // 创建树
        List<BasicPairNode> topNodes = nodes.parallelStream()
                .filter(node -> SysConstant.DEFAULT_PARENT_ID.equals(node.getParentId())).collect(Collectors.toList());
        topNodes.parallelStream().forEach(node -> {
            createTree(node, nodes);
        });
        return topNodes;
    }

    private void createTree(BasicPairNode curNode, List<BasicPairNode> nodes) {
        List<BasicPairNode> nextNodes = nodes.parallelStream()
                .filter(node -> StringUtils.equals(node.getParentId(), curNode.getValue()))
                .sorted().collect(Collectors.toList());
        nextNodes.parallelStream().forEach(node -> {
            createTree(node, nodes);
        });
        curNode.setChildren(nextNodes);
        curNode.setLeaf(CollectionUtils.isNotEmpty(nextNodes) ? LeafType.NO : LeafType.YES);
    }
}
