package com.ranyikang.ssh.aop;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * CLASS_NAME: ApiAop.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 对 api 接口的响应进行统一封装类  <br/>
 * @date: 2022-09-13 <br/>
 */
@Slf4j
@Aspect
@Component
public class ApiAop {

    /**
     * 使用环绕增强,对所有暴露对外的 api 接口进行处理,对返回的异常信息进行包装,使返回的异常和正常响应均返回相同的处理对象,便于前端的处理
     *
     * @param pjp 进行连接点对象
     * @return 返回请求响应对象
     */
    @Around(value = "execution(* com.ranyikang.ssh.api.*.*(..))")
    public Response around(ProceedingJoinPoint pjp) {
        // 正常处理业务
        try {
            log.info("----执行处理----");
            return (Response) pjp.proceed();
        }
        // 满足自定义异常捕获
        catch (BusinessException busException) {
            log.info("----自定义业务异常处理----");
            return Response.builder().code(406).message(busException.getMessage()).build();
        }
        // 其他异常捕获
        catch (Throwable e) {
            log.error("----其他异常----");
            log.error("本次异常信息为: {}", e.getMessage());
            return Response.builder().code(407).message("系统发生异常,请联系管理员").build();
        }
    }

}
