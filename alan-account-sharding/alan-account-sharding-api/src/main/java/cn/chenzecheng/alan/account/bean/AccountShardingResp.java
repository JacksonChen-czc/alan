package cn.chenzecheng.alan.account.bean;

import lombok.Data;

/**
 * 账号信息返回对象
 *
 * @author chenzc
 * @date 2022/7/18 18:01
 */
@Data
public class AccountShardingResp {

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 用户账号
     */
    private String accountNo;

    /**
     * 用户名称
     */
    private String accountName;

    /**
     * 用户手机号
     */
    private String accountMobile;

    /**
     * 用户性别，0未知，1男，2女
     */
    private Byte accountGender;

}
