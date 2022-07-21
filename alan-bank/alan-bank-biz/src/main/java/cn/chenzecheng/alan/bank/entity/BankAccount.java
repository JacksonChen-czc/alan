package cn.chenzecheng.alan.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author JacksonChen
 * @since 2022-07-21
 */
@TableName("t_bank_account")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 银行账户id
     */
    @TableId(value = "bank_account_id", type = IdType.ASSIGN_ID)
    private Long bankAccountId;

    /**
     * 用户id
     */
    private Long accountId;

    /**
     * 用户余额
     */
    private BigDecimal banlance;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBanlance() {
        return banlance;
    }

    public void setBanlance(BigDecimal banlance) {
        this.banlance = banlance;
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
        return "BankAccount{" +
                "bankAccountId = " + bankAccountId +
                ", accountId = " + accountId +
                ", banlance = " + banlance +
                ", updateTime = " + updateTime +
                ", createTime = " + createTime +
                "}";
    }
}
