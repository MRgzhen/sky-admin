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
public class DicSearchVo extends PageEntity {

    @ApiModelProperty(value = "字典名称")
    private String label;

    @ApiModelProperty(value = "字典编码")
    private String code;
}
