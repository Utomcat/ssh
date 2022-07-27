package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CLASS_NAME: TestApi.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 测试API 类  <br/>
 * @date: 2022-07-14 <br/>
 */
@RestController
@RequestMapping("test")
public class TestApi {

    /**
     * 测试业务处理对象
     */
    private TestService testService;

    /**
     * 测试业务对象 set 注入方法
     *
     * @param testService 测试业务对象
     */
    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    /**
     * 全局变量测试方法
     *
     * @return 返回响应结果封装对象
     */
    @GetMapping("global/variable")
    public Response testGlobalVariable() {
        testService.testMethodZero();
        return Response.SUCCESS;
    }

    /**
     * 形实参数测试
     *
     * @return 返回响应结果封装对象
     */
    @GetMapping("formAndReal/param")
    public Response testFormAndRealParam() {
        return Response.valueOfObject(testService.testFormAndRealParam());
    }
}
