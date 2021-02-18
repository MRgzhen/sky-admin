package com.yanyu.sky.sys.bean.vo.basic;

import com.fasterxml.jackson.annotation.JsonValue;
import com.yanyu.sky.sys.bean.enums.EnabledType;
import com.yanyu.sky.sys.bean.enums.LeafType;
import lombok.*;

import java.util.List;

/**
 * @author yanyu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BasicPairNode implements Comparable<BasicPairNode>{

    private String id;
    private String value;
    private String label;
    private String parentId;
    private EnabledType enabled;
    private boolean disabled;
    private Integer sort;
    private LeafType leaf;
    private String type;
    private List<BasicPairNode> children;

    public boolean getDisabled() {
        return EnabledType.DISABLED.equals(enabled);
    }

    @Override
    public int compareTo(BasicPairNode o) {
        if(sort == null) {
            return 1;
        } else if(o.getSort() == null) {
            return -1;
        } else {
            return sort - o.getSort();
        }
    }
}
