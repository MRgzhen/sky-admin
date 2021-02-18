package com.yanyu.sky.sys.bean.vo.dic;

import com.yanyu.sky.common.valid.group.AddGroup;
import com.yanyu.sky.common.valid.group.UpdateEnabledGroup;
import com.yanyu.sky.common.valid.group.UpdateGroup;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.IsDefaultType;
import com.yanyu.sky.sys.bean.vo.dic.valid.UpdateIsDefaultGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author yanyu
 */
@Data
@ToString
public class DicItemSaveVo {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class, UpdateIsDefaultGroup.class, UpdateEnabledGroup.class})
    @Null(message = "{Null}",groups = {AddGroup.class})
    private String id;

    @ApiModelProperty(value = "字典编码")
    @NotNull(message = "{NotNull}",groups = {UpdateGroup.class, UpdateIsDefaultGroup.class, UpdateEnabledGroup.class})
    private String code;

    @ApiModelProperty(value = "字典项值")
    private String value;

    @ApiModelProperty(value = "字典项名称")
    private String name;

    @ApiModelProperty(value = "启用状态, 1：启用；0：禁用")
    private EnabledType enabled;

    @ApiModelProperty(value = "默认项（数据子项可以设置默认项，有且只有一个）, 1：默认；0：不是默认")
    private IsDefaultType isDefault;

    @ApiModelProperty(value = "字典顺序， 由小到大")
    private Integer sort;

    @ApiModelProperty(value = "数据字典描述")
    private String remark;
}
