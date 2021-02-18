package com.yanyu.sky.sys.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.common.bean.DataScopeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据权限测试
 * @author yanyu
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_datascope_test")
@ApiModel(value="DatascopeTest对象", description="数据权限测试")
public class DatascopeTest extends DataScopeEntity {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键")
        private String id;

      @ApiModelProperty(value = "数据权限测试")
      private String name;

      @ApiModelProperty(value = "部门id")
      private String ds_dept;


}
