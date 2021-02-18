package com.yanyu.sky.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanyu.sky.sys.bean.po.Social;
import com.yanyu.sky.sys.bean.vo.social.SocialOfUserInfoVo;
import com.yanyu.sky.sys.bean.vo.social.SocialOfUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能: 社交 Mapper接口
 * @author yanyu
 * @date 2021-01-20
 */
public interface SocialMapper extends BaseMapper<Social> {

    List<SocialOfUserVo> listByUserId(String userId);

}