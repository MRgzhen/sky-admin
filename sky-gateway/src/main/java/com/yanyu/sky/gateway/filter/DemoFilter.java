package com.yanyu.sky.gateway.filter;

import com.github.mrgzhen.core.exception.GeneralException;
import com.github.mrgzhen.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanyu
 */
//@Component
@Slf4j
public class DemoFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpMethod httpMethod = exchange.getRequest().getMethod();
        if(httpMethod != HttpMethod.GET) {
            throw new ServiceException("演示环境不能修改");
        }
        return chain.filter(exchange);
    }
}
