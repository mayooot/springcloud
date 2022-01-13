package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月13日 19:44:00
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----- PaymentFallbackService fall back - paymentInfo_OK -----";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "----- PaymentFallbackService fall back - paymentInfo_Timeout -----";
    }
}
