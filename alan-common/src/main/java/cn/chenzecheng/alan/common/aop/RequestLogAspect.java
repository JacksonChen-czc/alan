package cn.chenzecheng.alan.common.aop;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
@Slf4j
public class RequestLogAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution(public * cn.chenzecheng.alan..controller..*(..))")
    public void requestServer() {
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //记录请求开始执行时间：
        long beginTime = System.currentTimeMillis();
        //获取请求信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        //获取代理地址、请求地址、请求类名、方法名
//        String remoteAddress = IPUtils.getProxyIP(request);
        String requestURI = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getSimpleName();

        //获取请求参数：
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        //获取请求参数类型
        String[] parameterNames = ms.getParameterNames();
        //获取请求参数值
        Object[] parameterValues = pjp.getArgs();
        StringBuilder sb = new StringBuilder();
        //组合请求参数，进行日志打印
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    sb.append("[").
                            append(parameterNames[i]).append("=").append(parameterValues[i])
                            .append("]");
                } else {
                    sb.append("[").
                            append(parameterNames[i]).append("=")
                            .append(JSONUtil.toJsonStr(parameterValues[i]))
                            .append("]");
                }
            }
        }
        Object result;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            //请求操纵失败,记录错误日志
            log.error("请求异常！IP【...】 URI：【{}】 控制类：【{}】 方法：【{}】 请求参数：【{}】", requestURI, clazzName, methodName, sb.toString());
            throw throwable;
        }
        //请求操作成功
        String resultJosnString = "";
        if (result != null) {
            if (isPrimitive(result.getClass()) || String.class.equals(result.getClass())) {
                resultJosnString = String.valueOf(result);
            } else {
                resultJosnString = JSONUtil.toJsonStr(result);
            }
        }
        //记录请求完成执行时间：
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - beginTime;
        //记录日志
        log.info("请求成功！耗时【{}】 IP【...】  URI【{}】 控制类【{}】 方法【{}】 参数【{}】 返回【{}】", usedTime, requestURI, clazzName,
                methodName, sb.toString(), resultJosnString);
        return result;
    }

    private boolean isPrimitive(Class<?> returnType) {
        try {
            // 判断是否原始类型或者其包装类型
            return returnType.isPrimitive() || ((Class<?>) returnType.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }
}
