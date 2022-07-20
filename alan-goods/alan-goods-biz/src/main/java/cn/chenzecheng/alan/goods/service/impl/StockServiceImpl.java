package cn.chenzecheng.alan.goods.service.impl;

import cn.chenzecheng.alan.goods.entity.Stock;
import cn.chenzecheng.alan.goods.mapper.StockMapper;
import cn.chenzecheng.alan.goods.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品库存表 服务实现类
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-19
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
