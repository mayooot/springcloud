package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月09日 21:17:00
 */
@Configuration
public class ApplicationContextConfig {
    /**
     * 相当于 applicationContext.xml <bean id="" class=""></bean>
     * @return RestTemplate实例对象
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
