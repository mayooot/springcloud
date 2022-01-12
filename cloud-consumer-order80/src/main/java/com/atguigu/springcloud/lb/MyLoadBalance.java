package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月12日 16:03:00
 */
@Component
public class MyLoadBalance implements LoadBalance{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            // 获取atomicInteger的值，初始值为0
            current = this.atomicInteger.get();
            // 2147483647为Integer的最大值，如果大于它，则置零，小于得让current + 1
            // next为第几次访问
            next = current >= 2147483647 ? 0 : current + 1;
            // compareAndSet(期望值，要修改的值),如果期望值等于要修改的值，则返回true并修改atomicInteger的值
        } while (!this.atomicInteger.compareAndSet(current, next));

        System.out.println("*****访问次数next：" +next);
        return next;
    }

    /**
     * 负载均衡算法：rest接口第几次请求 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后rest接口计数从1开始
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
