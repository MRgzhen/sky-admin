package com.yanyu.sky.auth;


import com.yanyu.sky.common.constant.SkyAppConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yanyu
 */
@SpringBootApplication
@EnableFeignClients(value = SkyAppConstant.FEIGN_BASE_PACKAGE)
public class SkyAuthApplication {

    public static void main(String[] args){
        System.out.println();
        SpringApplication.run(SkyAuthApplication.class, args);
    }

}
