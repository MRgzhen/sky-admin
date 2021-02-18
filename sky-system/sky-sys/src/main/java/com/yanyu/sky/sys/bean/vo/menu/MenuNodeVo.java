package com.yanyu.sky.sys.bean.vo.menu;

import com.yanyu.sky.sys.bean.po.Menu;
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
public class MenuNodeVo extends Menu implements Comparable<MenuNodeVo>{
    private List<MenuNodeVo> children;

    @Override
    public int compareTo(MenuNodeVo o) {
        if(super.getSort() == null) {
            return 1;
        } else if(o.getSort() == null) {
            return -1;
        } else {
            return super.getSort() - o.getSort();
        }
    }
}
