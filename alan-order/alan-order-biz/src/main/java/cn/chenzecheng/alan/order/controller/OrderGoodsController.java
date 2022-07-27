package cn.chenzecheng.alan.order.controller;

import cn.chenzecheng.alan.common.bean.MyPageInfo;
import cn.chenzecheng.alan.common.bean.MyPageResult;
import cn.chenzecheng.alan.order.bean.GoodsOrderListReq;
import cn.chenzecheng.alan.order.bean.GoodsOrderResp;
import cn.chenzecheng.alan.order.entity.OrderGoods;
import cn.chenzecheng.alan.order.service.IOrderGoodsService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单商品表 前端控制器
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@RestController
@RequestMapping("/order/goods")
public class OrderGoodsController {

    @Resource
    private IOrderGoodsService orderGoodsService;

    @PostMapping("/list")
    public MyPageResult<GoodsOrderResp> goodsOrderList(@RequestBody GoodsOrderListReq req) {
        IPage<OrderGoods> page = new Page<>(req.getPageNo(), req.getSize());
        LambdaQueryWrapper<OrderGoods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderGoods::getGoodsId, req.getGoodsId());
        IPage<OrderGoods> resultPage = orderGoodsService.page(page, wrapper);
        MyPageInfo pageInfo = new MyPageInfo(resultPage.getCurrent(), resultPage.getSize(), resultPage.getPages(), resultPage.getTotal());
        return MyPageResult.ok(BeanUtil.copyToList(resultPage.getRecords(), GoodsOrderResp.class, CopyOptions.create()), pageInfo);
    }
}
