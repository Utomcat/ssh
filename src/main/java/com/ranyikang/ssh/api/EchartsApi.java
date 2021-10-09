package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.service.EchartsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * CLASS_NAME: EChartsApi.java <br/>
 * Description: ECharts 接口API <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 08
 */
@RestController
@RequestMapping("/echarts/")
public class EchartsApi {

    @Resource
    private EchartsServiceImpl echartsService;

    @GetMapping("sunburstDrink")
    public Response sunburstDrink(){
        return Response.valueOfObject(echartsService.getData());
    }

}
