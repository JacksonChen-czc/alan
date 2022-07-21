package cn.chenzecheng.alan.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单商品表
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@TableName("t_order_goods")
@Data
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    @TableId(value = "order_goods_id", type = IdType.ASSIGN_ID)
    private Long orderGoodsId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 商品总价
     */
    private BigDecimal goodsPriceSum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
