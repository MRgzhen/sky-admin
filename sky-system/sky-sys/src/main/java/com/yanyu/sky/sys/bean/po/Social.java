package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import com.yanyu.sky.sys.bean.enums.SocialSexType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能: 社交 实体类
 * @author yanyu
 * @date 2021-01-20
 */
@Data
@TableName("sys_social")
@ApiModel(value = "Social对象}", description = "社交")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Social extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "用户表中的id")
    private String userId;
    @ApiModelProperty(value = "社交标识，如：weixin，qq")
    private String app;
    @ApiModelProperty(value = "社交凭证，如：openid")
    private String openId;
    @ApiModelProperty(value = "社交凭证，如：unionid")
    private String unionId;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    @ApiModelProperty(value = "机构")
    private String company;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "性别")
    private SocialSexType sex;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "token")
    private String accessToken;
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    @ApiModelProperty(value = "token有效时间")
    private Long expireTime;
}