package com.yanyu.sky.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanyu.sky.sys.bean.enums.MenuType;
import com.yanyu.sky.sys.bean.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单 Mapper 接口
 * @author yanyu
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> listMenusByRoleIds(@Param("roleIds")Set<String> roleIds,@Param("types") List<MenuType> types, @Param("enabled")String enabled);
}
