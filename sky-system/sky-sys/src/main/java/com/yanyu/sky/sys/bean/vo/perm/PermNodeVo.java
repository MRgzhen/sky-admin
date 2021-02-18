package com.yanyu.sky.sys.bean.vo.perm;

import com.yanyu.sky.sys.bean.po.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author yanyu
 */
@Data
public class PermNodeVo extends Menu implements Comparable<PermNodeVo>{
    private List<PermNodeVo> children;
    private String menuName;// 所属菜单名称

    @Override
    public int compareTo(PermNodeVo o) {
        if(super.getSort() == null) {
            return 1;
        } else if(o.getSort() == null) {
            return -1;
        } else {
            return super.getSort() - o.getSort();
        }
    }
}
