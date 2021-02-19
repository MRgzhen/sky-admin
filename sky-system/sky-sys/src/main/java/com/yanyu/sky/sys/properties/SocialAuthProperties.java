package com.yanyu.sky.sys.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author yanyu
 */
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "yanyu.social")
public class SocialAuthProperties {

    private Map<String, App> app;

    @Data
    public static class App {
        private String redirectUrl;

        private String clientId;

        private String clientSecret;
    }
}
