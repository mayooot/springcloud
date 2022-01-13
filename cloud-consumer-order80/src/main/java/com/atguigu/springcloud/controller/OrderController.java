package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月09日 21:14:00
 */
@RestController
@Slf4j
public class OrderController {
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * RestTemplate发送的post请求，默认把对象序列化为JSON格式，控制器接收时，要在参数上加上@requestBody，
     * 反序列化为java对象
     * @param payment 要插入的payment对象
     * @return 返回对象为响应体中数据转化成的对象，基本可以理解成Json
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    /**
     * 通过RestTemplate发送请求到提供者模块
     * @param id 查询对象的id
     * @return 返回对象为响应体中数据转化成的对象，基本可以理解成Json
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * RestTemplate发送的post请求，默认把对象序列化为JSON格式，控制器接收时，要在参数上加上@requestBody，
     * 反序列化为java对象
     * @param payment 要插入的payment对象
     * @return 返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
     */
    @GetMapping("/consumer/payment/createForEntity")
    public CommonResult<Payment> create2(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    /**
     * 通过RestTemplate发送请求到提供者模块
     * @param id 查询对象的id
     * @return 返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }


    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        // 通过discoverClient.getInstances方法获取所有名为CLOUD-PAYMENT-SERVICE的提供者实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        // 调用自己写的LoadBalance接口,插入instances实例
        ServiceInstance serviceInstance = loadBalance.instances(instances);

        // 获取访问地址
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
