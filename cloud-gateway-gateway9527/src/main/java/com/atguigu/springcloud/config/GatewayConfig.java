package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月14日 21:34:00
 */
@Configuration
public class GatewayConfig {
    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址：https://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_guonei",
                r -> r.path("/guonei")
                        .uri("https://news.baidu.com/guonei")).build();
        return routes.build();
    }

    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址 http://localhost:9527/mil时会自动转发到地址：https://news.baidu.com/mil
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("paht_route_mil",
                r -> r.path("/mil")
                        .uri("https://news.baidu.com/mil")).build();

        return  routes.build();
    }
}
