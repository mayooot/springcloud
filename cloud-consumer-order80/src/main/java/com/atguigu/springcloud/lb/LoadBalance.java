package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月12日 15:59:00
 */
public interface LoadBalance {
    ServiceInstance instances(List<ServiceInstance> instances);
}
