package com.yanyu.sky.sys.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mrgzhen.core.exception.ServiceException;
import com.github.mrgzhen.core.mybatis.SqlParserContext;
import com.github.mrgzhen.core.security.LoginUser;
import com.github.mrgzhen.core.security.LoginUserContext;
import com.yanyu.sky.common.constant.SkyAppConstant;
import com.yanyu.sky.sys.bean.po.Social;
import com.yanyu.sky.sys.bean.vo.social.*;
import com.yanyu.sky.sys.dao.SocialMapper;
import com.yanyu.sky.sys.service.ISocialService;
import com.yanyu.sky.sys.util.SocialUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能: 社交 业务实现类
 * @author yanyu
 * @date 2021-01-20
 */
@Service
@Slf4j
public class SocialServiceImpl extends ServiceImpl<SocialMapper, Social> implements ISocialService {

    @Autowired
    private SocialUtil socialUtil;

    @Override
    public IPage<Social> listPage(Integer pageNum, Integer pageSize, Social vo) {
        return baseMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Social>lambdaQuery(vo));
    }

    @Override
    public List<SocialOfUserVo> listByUserId(String userId) {
        List<SocialOfUserVo> socials = baseMapper.listByUserId(userId);
        for(int i = 0;i < SkyAppConstant.SOCIALS.size();i++) {
            if(CollectionUtils.containsAny(socials, SkyAppConstant.SOCIALS.get(i))) {
                SocialOfUserVo ofVo = new SocialOfUserVo();
                ofVo.setStatus("0");
                ofVo.setApp(SkyAppConstant.SOCIALS.get(i));
                socials.add(ofVo);
            }
        }
        return socials;
    }

    @Override
    @Transactional
    public SocialAuthVo addAuth(AuthUser authUser) {
        Social social = socialUtil.socialBuilder(authUser);
        Social repSocial = baseMapper.selectOne(Wrappers.<Social>lambdaQuery().eq(Social::getOpenId, social.getOpenId()).eq(Social::getApp, social.getApp()));
        SqlParserContext.builder().autoFill(social.getOpenId(), social.getOpenId()).hold();
        if(repSocial == null) {
            baseMapper.insert(social);
        } else {
            baseMapper.update(social, Wrappers.<Social>lambdaUpdate().eq(Social::getOpenId, social.getOpenId()).eq(Social::getApp, social.getApp()));
        }

        repSocial = baseMapper.selectOne(Wrappers.<Social>lambdaQuery().eq(Social::getOpenId, social.getOpenId()).eq(Social::getApp, social.getApp()));
        SocialAuthVo vo = SocialAuthVo.builder().app(repSocial.getApp()).avatar(repSocial.getAvatar())
                .openId(repSocial.getOpenId()).nickName(repSocial.getNickName()).userId(repSocial.getUserId()).build();
        // 设置用户是否已经关联过或没有关联，0：没有；1：关联
        if(StringUtils.isBlank(repSocial.getUserId())) {
            vo.setAuth("0");
        } else {
            vo.setAuth("1");
        }
        // 获取tmpCode，用于绑定和登录
        String tmpCode = socialUtil.putAndGetTmpCode(vo.getApp(), vo.getOpenId());
        vo.setTmpCode(tmpCode);
        return vo;
    }

    @Override
    @Transactional
    public void bindSocial(SocialBindVo vo) {
        check(vo);
        LoginUser loginUser = LoginUserContext.getLoginUserThrow();
        String userId = loginUser.getUserId();
        Social social = Social.builder().userId(userId).build();
        baseMapper.update(social, Wrappers.<Social>lambdaUpdate().eq(Social::getOpenId, vo.getOpenId()).eq(Social::getApp, vo.getApp()));
        socialUtil.delTmpCode(vo.getApp(), vo.getTmpCode());
    }

    @Override
    @Transactional
    public void unbindSocial(SocialUnBindVo vo) {
        LoginUser loginUser = LoginUserContext.getLoginUserThrow();
        String userId = loginUser.getUserId();
        baseMapper.update(null, Wrappers.<Social>lambdaUpdate().eq(Social::getUserId, userId).eq(Social::getApp, vo.getApp()).set(Social::getUserId, null));
    }

    private void check(SocialBindVo vo) {
        String openId = socialUtil.getTmpCode(vo.getApp(), vo.getTmpCode());
        log.info("根据tmpCode校验当前请求openId, 缓存中信息=[openId:{}]，请求信息=[tmpCode:{},app:{}]", openId, vo.getTmpCode(), vo.getApp());
        if(!StringUtils.equals(openId, vo.getOpenId())) {
            throw new ServiceException("参数错误");
        }
    }
}