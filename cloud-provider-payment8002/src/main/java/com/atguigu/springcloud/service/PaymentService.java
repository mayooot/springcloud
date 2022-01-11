package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月09日 19:53:00
 */
public interface PaymentService {
    /**
     * 插入一条数据
     * @param payment 要插入的payment对象
     * @return 影响的行数
     */
    int create(Payment payment);

    /**
     * 根据id查询payment对象
     * @param id 要查询对象的id
     * @return 查询到的payment对象
     */
    Payment getPaymentById(@Param("id") Long id);
}
