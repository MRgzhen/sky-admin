package com.yanyu.sky.sys.bean.vo.dic;

import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.DicType;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author yanyu
 */
@Data
@ToString
public class DicSaveVo {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class, UpdateEnabledGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "字典编码")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String label;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    private EnabledType enabled;

    @ApiModelProperty(value = "数据字典类型；0：预定义 ；1：自定义")
    private DicType type;

    @ApiModelProperty(value = "字典顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "数据字典描述")
    private String remark;
}
