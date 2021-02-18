package com.yanyu.sky.generator.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据库配置
 * @author yanyu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gen_ds_setting")
@ApiModel(value="DsSetting对象", description="数据库配置")
public class DsSetting extends BaseEntity {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键")
      private String id;

      @ApiModelProperty(value = "数据库真实名字")
      private String name;

      @ApiModelProperty(value = "数据库名字昵称")
      private String nickName;

      @ApiModelProperty(value = "驱动")
      private String driver;

      @ApiModelProperty(value = "连接地址")
      private String url;

      @ApiModelProperty(value = "连接用户名")
      private String username;

      @ApiModelProperty(value = "连接密码")
      private String password;

      @ApiModelProperty(value = "部门id")
      private String dsDept;

}
