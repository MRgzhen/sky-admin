package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.common.bean.IdsEntity;
import com.yanyu.sky.sys.bean.po.Menu;
import com.yanyu.sky.sys.bean.vo.perm.PermNodeVo;
import com.yanyu.sky.sys.bean.vo.perm.PermSaveVo;
import com.yanyu.sky.sys.bean.vo.perm.PermSearchVo;

import java.util.List;

/**
 * 系统权限 业务接口
 * @author yanyu
 */
public interface IPermService extends IService<Menu> {

    List<PermNodeVo> listPermByMenuId(PermSearchVo vo);

    void add(PermSaveVo vo);

    void update(PermSaveVo vo);

    void delete(IdsEntity vo);
}
