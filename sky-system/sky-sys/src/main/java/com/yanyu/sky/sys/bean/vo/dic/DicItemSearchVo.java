package com.yanyu.sky.sys.bean.vo.dic;

import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanyu
 */
@Data
@ToString(callSuper = true)
public class DicItemSearchVo extends PageEntity {

    @ApiModelProperty(value = "字典编码")
    private String code;

    @ApiModelProperty(value = "字典项值")
    private String value;

    @ApiModelProperty(value = "字典项名称")
    private String name;
}
