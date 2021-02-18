package com.yanyu.sky.sys.bean.vo.dept;

import com.yanyu.sky.sys.bean.po.Dept;
import lombok.*;

import java.util.List;

/**
 * @author yanyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class DeptNodeVo extends Dept implements Comparable<DeptNodeVo>{
    private List<DeptNodeVo> children;

    @Override
    public int compareTo(DeptNodeVo o) {
        if(super.getSort() == null) {
            return 1;
        } else if(o.getSort() == null) {
            return -1;
        } else {
            return super.getSort() - o.getSort();
        }
    }
}
