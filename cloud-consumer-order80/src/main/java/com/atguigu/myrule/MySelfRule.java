package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月11日 21:49:00
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        // 切换为随机
        return new RandomRule();
    }
}
