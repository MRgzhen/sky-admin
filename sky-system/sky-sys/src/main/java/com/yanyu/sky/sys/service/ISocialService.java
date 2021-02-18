package com.yanyu.sky.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yanyu.sky.sys.bean.po.Social;
import com.yanyu.sky.sys.bean.vo.social.*;
import me.zhyd.oauth.model.AuthUser;

import java.util.List;

/**
 * 功能: 社交 业务接口
 * @author yanyu
 * @date 2021-01-20
 */
public interface ISocialService extends IService<Social> {

    IPage<Social> listPage(Integer pageNum, Integer pageSize, Social vo);

    List<SocialOfUserVo> listByUserId(String userId);

    SocialAuthVo addAuth(AuthUser authUser);

    void bindSocial(SocialBindVo vo);

    void unbindSocial(SocialUnBindVo vo);
}