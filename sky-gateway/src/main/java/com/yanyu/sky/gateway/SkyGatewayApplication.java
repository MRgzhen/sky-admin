package com.yanyu.sky.gateway;

import com.github.mrgzhen.gateway.EnableYanyuGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanyu
 */
@SpringBootApplication
@EnableYanyuGateway
public class SkyGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(SkyGatewayApplication.class, args);
    }
}
