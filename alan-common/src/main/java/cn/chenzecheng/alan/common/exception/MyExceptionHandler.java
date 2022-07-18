package cn.chenzecheng.alan.common.exception;

import cn.chenzecheng.alan.common.bean.MyResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author JacksonChen
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public MyResult<Void> bizExceptionHandler(BizException e) {
        log.warn("捕获到业务异常", e);
        return MyResult.error("业务异常：" + e.getMessage());
    }

    @ExceptionHandler(value = BlockException.class)
    @ResponseBody
    public MyResult<Void> blockExceptionHandler(BlockException e) {
        log.warn("捕获到熔断异常", e);
        return MyResult.error("熔断异常：" + e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public MyResult<Void> exceptionHandler(Exception e) {
        log.warn("捕获到系统异常", e);
        return MyResult.error("系统异常：" + e.getMessage());
    }
}