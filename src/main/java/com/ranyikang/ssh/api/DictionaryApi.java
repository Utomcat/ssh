package com.ranyikang.ssh.api;

import com.ranyikang.ssh.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CLASS_NAME: DictionaryApi.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 字典 API 接口  <br/>
 * @date: 2022-07-13 <br/>
 */
@RestController
@RequestMapping("dict")
public class DictionaryApi {

    /**
     * 字典业务对象
     */
    private DictionaryService dictionaryService;

    /**
     * 字典业务对象 set 注入方法
     *
     * @param dictionaryService 字典业务对象
     */
    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
