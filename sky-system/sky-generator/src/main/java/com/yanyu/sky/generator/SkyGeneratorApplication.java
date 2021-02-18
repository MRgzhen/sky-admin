package com.yanyu.sky.generator;


import com.yanyu.sky.common.constant.SkyAppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanyu
 * @date 2020/9/30
 */
@SpringBootApplication
@MapperScan(SkyAppConstant.DAO_BASE_PACKAGE)
public class SkyGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyGeneratorApplication.class, args);
    }

}
