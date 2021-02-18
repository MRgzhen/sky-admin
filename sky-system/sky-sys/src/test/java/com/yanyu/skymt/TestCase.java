package com.yanyu.skymt;

import com.yanyu.sky.sys.SkySysApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * <br/>功能:
 * <br/>版本: 1.0
 * <br/>开发人员: yanyu
 * <br/>创建日期: 2020/11/26 10:02
 * <br/>修改日期: 2020/11/26 10:02
 * <br/>修改列表:
 */
@SpringBootTest(classes= {SkySysApplication.class})
public class TestCase {

    @Autowired
    private RestTemplateBuilder restTemplate;
    @Test
    public void test() {
        System.out.println("====================");
        System.out.println(restTemplate);
    }
}
