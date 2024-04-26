package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.entity.Student;
import com.ranyikang.ssh.service.TestService;
import lombok.val;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

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

    private static final Logger log = LoggerFactory.getLogger(TestApi.class);
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


    @GetMapping("testJpaOntToMany")
    public Response testJpaOntToMany() {
        return Response.valueOfObject(testService.testJpaOneToMany());
    }


    @PostMapping("save")
    public Response save(@RequestBody Student student) {
        return Response.valueOfObject(testService.testSave(student));
    }

    @PostMapping("upload/image")
    public Response uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            log.info("文件名: ==> {}", file.getOriginalFilename());
            log.info("文件类型: ==> {}", file.getContentType());
            val image = ImageIO.read(file.getInputStream());
            log.info("文件长 ==> {}", image.getWidth());
            log.info("文件宽 ==> {}", image.getHeight());
            ImageInfo imageInfo = Imaging.getImageInfo(file.getInputStream(), file.getOriginalFilename());
            log.info("文件水平DPI ==> {}", imageInfo.getPhysicalWidthDpi());
            log.info("文件垂直DPI ==> {}", imageInfo.getPhysicalHeightDpi());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Response.SUCCESS;
    }

}
