package com.yanyu.sky.sys.bean.vo.role;

import com.yanyu.sky.sys.bean.enums.LeafType;
import lombok.*;

/**
 * @author yanyu
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfRoleVo {

    private String id;

    private LeafType leaf;// 是否是叶子节点，1是，0不是

    private String parentId;
}
