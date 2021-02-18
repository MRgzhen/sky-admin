package com.yanyu.sky.generator.bean.vo.basic;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author yanyu
 * @date 2020/7/18
 */
@Data
@ToString
@Builder
public class BasicPair {

    private String value;
    private String label;
    private boolean disabled;
}
