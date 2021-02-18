package com.yanyu.sky.sys;

import com.github.mrgzhen.security.config.EnableYanyuResourceServer;
import com.yanyu.sky.common.constant.SkyAppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanyu
 * @date 2020/6/30
 */
@SpringBootApplication
@MapperScan(SkyAppConstant.DAO_BASE_PACKAGE)
public class SkySysApplication {

    public static void main(String[] args){
        SpringApplication.run(SkySysApplication.class, args);
    }
}