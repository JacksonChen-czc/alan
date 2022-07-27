package cn.chenzecheng.alan.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@TableName("t_order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 用户id
     */
    private Long accountId;

    /**
     * 订单金额，单位：分
     */
    private Long orderPrice;

    /**
     * 订单状态，1已创建，2已支付，3已取消
     */
    private Integer orderStatus;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
