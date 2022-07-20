package cn.chenzecheng.alan.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 商品库存表
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-19
 */
@TableName("t_stock")
@Getter
@Setter
@ToString
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存对应的商品id
     */
    @TableId(value = "goods_id", type = IdType.ASSIGN_ID)
    private Long goodsId;

    /**
     * 商品可售数量
     */
    private Integer saleNum;

    /**
     * 库存总数
     */
    private Integer total;
}
