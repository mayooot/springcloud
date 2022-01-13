package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月13日 17:38:00
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);

    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    // @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
    //         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    // })
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        int n = 10 / 0;
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙，请10秒钟后再试或者我自己运行出错，正在检查自己捏~";
    }

    // 全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理，请稍后再试捏！😈😈😈";
    }
}
