package cn.chenzecheng.alan.order.service.impl;

import cn.chenzecheng.alan.order.entity.OrderGoods;
import cn.chenzecheng.alan.order.mapper.OrderGoodsMapper;
import cn.chenzecheng.alan.order.service.IOrderGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品表 服务实现类
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoods> implements IOrderGoodsService {

}
