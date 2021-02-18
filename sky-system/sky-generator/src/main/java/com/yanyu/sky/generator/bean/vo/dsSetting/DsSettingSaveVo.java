package com.yanyu.sky.generator.bean.vo.dsSetting;

import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author yanyu
 * @date 2020/12/3
 */
@Data
public class DsSettingSaveVo {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "数据库真实名字")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String name;

    @ApiModelProperty(value = "数据库名字昵称")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String nickName;

    @ApiModelProperty(value = "驱动")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String driver;

    @ApiModelProperty(value = "连接地址")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String url;

    @ApiModelProperty(value = "连接用户名")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String username;

    @ApiModelProperty(value = "连接密码")
    @NotEmpty(message = "{NotEmpty}",groups = {UpdateGroup.class,AddGroup.class})
    private String password;
}
