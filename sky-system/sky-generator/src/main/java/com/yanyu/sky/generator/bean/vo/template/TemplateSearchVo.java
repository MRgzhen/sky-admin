package com.yanyu.sky.generator.bean.vo.template;

import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 模板
 * @author yanyu
 * @date 2020/12/1
 */
@Data
public class TemplateSearchVo extends PageEntity {

    @ApiModelProperty(value = "分组id")
    @NotEmpty(message = "{{NotEmpty}}")
    private String templateGroupId;

}
