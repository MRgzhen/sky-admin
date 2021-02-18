package com.yanyu.sky.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yanyu
 */
@EnableAdminServer
@SpringBootApplication
public class SkyMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyMonitorApplication.class, args);
    }

}
