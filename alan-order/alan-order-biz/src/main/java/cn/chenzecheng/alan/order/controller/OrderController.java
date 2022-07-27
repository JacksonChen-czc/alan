package cn.chenzecheng.alan.order.controller;

import cn.chenzecheng.alan.common.bean.MyResult;
import cn.chenzecheng.alan.common.exception.MyAssertUtil;
import cn.chenzecheng.alan.order.bean.AddOrderReq;
import cn.chenzecheng.alan.order.entity.Order;
import cn.chenzecheng.alan.order.entity.OrderGoods;
import cn.chenzecheng.alan.order.enums.OrderStatusEnum;
import cn.chenzecheng.alan.order.service.IOrderGoodsService;
import cn.chenzecheng.alan.order.service.IOrderService;
import org.assertj.core.util.Lists;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-19
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IOrderGoodsService orderGoodsService;

    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public MyResult<Boolean> addOrder(@RequestBody AddOrderReq req) {
        Order order = new Order();
        order.setAccountId(req.getAccountId());
        order.setOrderStatus(OrderStatusEnum.CREATED.getCode());
        long orderPrice = req.getOrderGoodsReqList().stream().mapToLong(AddOrderReq.OrderGoodsReq::getGoodsPriceSum).sum();
        order.setOrderPrice(orderPrice);
        boolean save = orderService.save(order);
        MyAssertUtil.isTrue(save);

        List<OrderGoods> orderGoodsList = Lists.newArrayList();
        for (AddOrderReq.OrderGoodsReq orderGoodsReq : req.getOrderGoodsReqList()) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getOrderId());
            orderGoods.setGoodsId(orderGoodsReq.getGoodsId());
            orderGoods.setAmount(orderGoodsReq.getGoodsAmount());
            orderGoods.setGoodsPriceSum(orderGoodsReq.getGoodsPriceSum());
            orderGoodsList.add(orderGoods);
        }
        orderGoodsService.saveBatch(orderGoodsList);
        return MyResult.ok(true);
    }

}
