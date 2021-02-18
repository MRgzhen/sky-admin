package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 职位
 * @author yanyu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_post")
@ApiModel(value = "Post对象", description = "职位")
public class Post extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "租户编码")
    private String tenantCode;

    @ApiModelProperty(value = "上级岗位")
    private String parentId;

    @ApiModelProperty(value = "岗位名")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
