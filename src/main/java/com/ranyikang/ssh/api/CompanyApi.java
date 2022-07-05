package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.exception.BusinessException;
import com.ranyikang.ssh.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * CLASS_NAME: CompanyApi.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 公司业务请求处理  <br/>
 * @date: 2022-07-05 <br/>
 */
@RestController
@RequestMapping("company")
public class CompanyApi {

    private CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @SuppressWarnings("unused")
    @PostMapping("afternoonParse")
    public Response afternoonParse(@RequestParam("file") MultipartFile file, @RequestParam("specialTreatment") String specialTreatment, HttpServletRequest request, HttpServletResponse response) {

        if (file.isEmpty()) {
            throw new BusinessException("不存在文件");
        }
        boolean special = StringUtils.hasText(specialTreatment);
        List<Map<String, Object>> maps = companyService.parseExcel(file, special);
        if (maps.size() > 0) {
            return Response.valueOfObject(maps);
        } else {
            return Response.valueOfObject("没有填写错误人员");
        }
    }


    @PostMapping("qrCode")
    public Response qrCode(@RequestParam("code") String code) {

        if (!StringUtils.hasText(code)) {
            throw new BusinessException("未录入未填报二维码人员信息!");
        }
        List<Map<String, Object>> maps = companyService.parseCode(code);
        if (maps.size() > 0) {
            return Response.valueOfObject(maps);
        } else {
            return Response.valueOfObject("没有未填报二维码人员");
        }
    }



}
