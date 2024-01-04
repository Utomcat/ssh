package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CLASS_NAME: PythonApi.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: python 程序接口  <br/>
 * @date: 2024-01-04 <br/>
 */


@RestController
@RequestMapping("/python")
public class PythonApi {

    /**
     * Python 接口业务类对象
     */
    private final PythonService pythonService;

    /**
     * 构造函数
     *
     * @param pythonService python 接口业务类对象
     */
    @Autowired
    public PythonApi(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @PostMapping("/get/json")
    public Response getJson() {
        return Response.valueOfObject(pythonService.getJson());
    }
}
