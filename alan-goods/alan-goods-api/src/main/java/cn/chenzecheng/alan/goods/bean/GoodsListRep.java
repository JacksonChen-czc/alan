package cn.chenzecheng.alan.goods.bean;

import cn.chenzecheng.alan.common.bean.BasePageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账号列表请求
 *
 * @author chenzc
 * @date 2022/7/18 18:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsListRep extends BasePageReq {

    private String name;
}
