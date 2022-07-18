package cn.chenzecheng.alan.common.exception;

import cn.chenzecheng.alan.common.enums.MyErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义业务异常
 *
 * @author JacksonChen
 * @date 2022/7/18 22:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(MyErrorEnum errorEnum) {
        super(errorEnum.getCode());
        this.errorCode = errorEnum.getCode();
        this.errorMsg = errorEnum.getDesc();
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


}
