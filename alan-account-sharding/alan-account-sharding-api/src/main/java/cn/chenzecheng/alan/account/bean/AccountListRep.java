package cn.chenzecheng.alan.account.bean;

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
public class AccountListRep extends BasePageReq {

    private String name;
    private String no;
    private String mobile;
}
