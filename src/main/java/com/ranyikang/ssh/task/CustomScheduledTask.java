package com.ranyikang.ssh.task;

import com.ranyikang.ssh.exception.NullResultException;
import com.ranyikang.ssh.exception.ParameterException;
import com.ranyikang.ssh.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * CLASS_NAME: CustomScheduledTask.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 自定义定时任务  <br/>
 * @date: 2022-06-22 <br/>
 */
@Slf4j
@Component
public class CustomScheduledTask {

    @Resource
    private UserServiceImpl userService;

    // @Scheduled(cron = "1/10 * * * * ? ")
    public void task1(){
        try{
            boolean admin = userService.dealMethod("Tom");
            log.info("执行结果为 {}", admin);
        }catch (Exception e){
            if (e.getClass().toString().equals(NullResultException.class.toString())){
                log.error("未查询到查询结果");
            } else if (e.getClass().toString().equals(ParameterException.class.toString())) {
                log.error("查询参数不存在!");
            }else {
                log.error("发生异常,异常信息为: {}", e.getMessage());
            }
        }
    }

}
