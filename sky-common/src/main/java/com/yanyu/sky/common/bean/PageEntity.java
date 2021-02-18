package com.yanyu.sky.common.bean;

import lombok.Setter;

/**
 * 分页实体类
 * @author yanyu
 */
@Setter
public class PageEntity {

    /** 当前页 */
    private Integer pageNum;

    /** 每页条数 */
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 20 : pageSize;
    }
}
