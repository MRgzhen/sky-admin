package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.Menu;
import com.yanyu.sky.sys.bean.vo.menu.MenuNodeVo;
import com.yanyu.sky.sys.bean.vo.menu.MenuSaveVo;

import java.util.List;

/**
 * 系统菜单 业务接口
 * @author yanyu
 */
public interface IMenuService extends IService<Menu> {

    List<MenuNodeVo> listLoginUserOfMenu();

    List<MenuNodeVo> lisMenu();

    void add(MenuSaveVo vo);

    void update(MenuSaveVo vo);

    void updateEnabled(MenuSaveVo vo);

    void delete(IdsEntity vo);
}
