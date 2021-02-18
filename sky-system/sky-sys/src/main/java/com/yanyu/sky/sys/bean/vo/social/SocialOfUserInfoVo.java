package com.yanyu.sky.sys.bean.vo.social;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author yanyu
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SocialOfUserInfoVo {

    @NotEmpty(message = "用户信息为空")
    private String userId;

    @NotEmpty
    private List<String> apps;
}
