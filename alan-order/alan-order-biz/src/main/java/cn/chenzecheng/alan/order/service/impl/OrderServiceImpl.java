package cn.chenzecheng.alan.order.service.impl;

import cn.chenzecheng.alan.order.entity.Order;
import cn.chenzecheng.alan.order.mapper.OrderMapper;
import cn.chenzecheng.alan.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
