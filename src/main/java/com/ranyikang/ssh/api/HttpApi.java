package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CLASS_NAME: HttpApi.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: Http 请求测试接口  <br/>
 * @date: 2024-02-01 <br/>
 */
@RestController
@RequestMapping("http")
public class HttpApi {

    /**
     * HTTP 请求业务类对象
     */
    private final HttpService httpService;

    /**
     * 构造函数
     *
     * @param httpService http 请求业务类对象示例
     */
    @Autowired
    public HttpApi(HttpService httpService) {
        this.httpService = httpService;
    }

    @PostMapping("/xml_test")
    public Response xmlTest(String xml) {
        return Response.valueOfObject(httpService.xmlTest(xml));
    }
}
