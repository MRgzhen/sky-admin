package com.yanyu.sky.generator.bean.vo.dsSetting;

import com.yanyu.sky.common.bean.PageEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author yanyu
 * @date 2020/11/18
 */
@Data
public class DsSettingSearcheVo extends PageEntity {

    @ApiModelProperty(value = "数据库名")
    private String dsId;
}
