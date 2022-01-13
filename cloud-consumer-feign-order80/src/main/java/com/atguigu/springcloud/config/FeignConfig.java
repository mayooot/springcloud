package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月13日 14:34:00
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
