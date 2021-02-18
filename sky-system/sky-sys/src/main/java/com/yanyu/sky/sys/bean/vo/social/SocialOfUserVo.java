package com.yanyu.sky.sys.bean.vo.social;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yanyu.sky.sys.bean.po.Social;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * @author yanyu
 * @date 2021/1/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class SocialOfUserVo extends Social {

    /** 绑定状态 1：绑定，0：未绑定*/
    private String status;
}
