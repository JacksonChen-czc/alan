package cn.chenzecheng.alan.account.bean;

import lombok.Data;

/**
 * 账号列表请求
 *
 * @author chenzc
 * @date 2022/7/18 18:43
 */
@Data
public class AccountListRep {

    private int pageNo;
    private int size;
    private String name;
    private String no;
    private String mobile;
}
