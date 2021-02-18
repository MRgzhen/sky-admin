package com.yanyu.sky.sys.bean.vo.basic;

import com.yanyu.sky.sys.bean.enums.EnabledType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author yanyu
 */
@Data
@ToString
@Builder
public class BasicPair {

    private String value;
    private String label;
    private EnabledType enabled;
    private boolean disabled;

    public boolean getDisabled() {
        return EnabledType.DISABLED.equals(enabled);
    }
}
