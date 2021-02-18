package com.yanyu.sky.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.util.AssertUtil;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.constant.SysConstant;
import com.yanyu.sky.sys.bean.enums.MenuType;
import com.yanyu.sky.sys.bean.po.Menu;
import com.yanyu.sky.sys.bean.vo.perm.PermNodeVo;
import com.yanyu.sky.sys.bean.vo.perm.PermSaveVo;
import com.yanyu.sky.sys.bean.vo.perm.PermSearchVo;
import com.yanyu.sky.sys.dao.MenuMapper;
import com.yanyu.sky.sys.service.IPermService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 系统权限 业务实现类
 * @author yanyu
 */
@Service
@Slf4j
public class PermServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IPermService {

    @Override
    public List<PermNodeVo> listPermByMenuId(PermSearchVo vo) {
        PermNodeVo topNode = new PermNodeVo();
        postCreateTree(vo.getMenuId(),vo,topNode);
        return topNode.getChildren();
    }

    @Override
    public void add(PermSaveVo vo) {
        canUpdate(null, vo.getPerms());

        Menu menu = new Menu();
        BeanUtils.copyProperties(vo, menu);
        baseMapper.insert(menu);
    }

    @Override
    public void update(PermSaveVo vo) {
        canUpdate(null, vo.getPerms());

        Menu menu = new Menu();
        BeanUtils.copyProperties(vo, menu);
        baseMapper.insert(menu);
    }

    @Override
    public void delete(IdsEntity vo) {
        baseMapper.deleteById(vo.getIds().iterator().next());
    }

    private void postCreateTree(String menuId, PermSearchVo vo, PermNodeVo curNode) {
        List<Menu> menus = baseMapper.selectList(Wrappers.<Menu>lambdaQuery()
                .eq(StringUtils.isNotBlank(menuId),Menu::getParentId, menuId)
                .eq(StringUtils.isNotBlank(vo.getPerm()),Menu::getPerms,vo.getPerm())
                .like(StringUtils.isNotBlank(vo.getName()),Menu::getName,vo.getName())
                .in(Menu::getType, MenuType.BUTTON, MenuType.OTHER));

        // 转换
        List<PermNodeVo> nextNodes = menus.stream().map(menu -> {
            PermNodeVo permNode = new PermNodeVo();
            BeanUtils.copyProperties(menu, permNode);
            if(SysConstant.DEFAULT_PARENT_ID.equals(menu.getParentId())) {
                permNode.setMenuName(SysConstant.MENU_TOP_NAME);
            } else {
                Optional.ofNullable(baseMapper.selectById(permNode.getParentId())).ifPresent((item)->permNode.setMenuName(item.getName()));
            }
            return permNode;
        }).sorted().collect(Collectors.toList());
        curNode.setChildren(nextNodes);

        // 创建树
        nextNodes.stream().forEach(node -> {
            postCreateTree(node.getId(),vo,node);
        });
    }

    private void canUpdate(String id,String perms) {
        int permCnt = baseMapper.selectCount(Wrappers.<Menu>lambdaQuery()
                .ne(StringUtils.isNoneBlank(id),Menu::getId, id)
                .eq(Menu::getPerms, perms));
        AssertUtil.isTrue(permCnt <= 0,"权限编码已经存在");
    }
}
