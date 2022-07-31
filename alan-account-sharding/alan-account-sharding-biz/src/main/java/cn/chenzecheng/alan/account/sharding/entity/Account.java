package cn.chenzecheng.alan.account.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-17
 */
@TableName("t_account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号id
     */
    @TableId(value = "account_id", type = IdType.ASSIGN_ID)
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

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public Byte getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(Byte accountGender) {
        this.accountGender = accountGender;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId = " + accountId +
                ", accountNo = " + accountNo +
                ", accountName = " + accountName +
                ", accountMobile = " + accountMobile +
                ", accountGender = " + accountGender +
                ", updateTime = " + updateTime +
                ", createTime = " + createTime +
                "}";
    }
}
