package com.ranyikang.ssh.api;

import com.ranyikang.ssh.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CLASS_NAME: PersonalApi.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 个人API 接口  <br/>
 * @date: 2022-07-08 <br/>
 */
@RestController
@RequestMapping("personal")
public class PersonalApi {

    /**
     * 个人业务对象
     */
    private PersonalService personalService;

    /**
     * 个人业务对象 set 注入方法
     *
     * @param personalService 个人业务对象
     */
    @Autowired
    public void setPersonalService(PersonalService personalService) {
        this.personalService = personalService;
    }


}
