package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.entity.Company;
import com.ranyikang.ssh.exception.BusinessException;
import com.ranyikang.ssh.service.CompanyService;
import com.ranyikang.ssh.vo.CompanyVO;
import com.ranyikang.ssh.vo.QrCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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

    /**
     * CompanyService 业务操作对象
     */
    private CompanyService companyService;

    /**
     * CompanyService set 注入方法
     *
     * @param companyService CompanyService 业务对象
     */
    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 下午行方二维码填写 excel 检查表格解析请求
     *
     * @param file             excel 源文件
     * @param specialTreatment 是否指定当天做特殊处理,非空则为需要特殊处理,空则不做特殊处理,如 今天周一但放假;
     * @param request          {@link HttpServletRequest} 对象
     * @param response         {@link  HttpServletResponse} 对象
     * @return 返回响应封装对象
     */
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

    /**
     * 上午二位码填报信息请求处理
     *
     * @param qrCode 二维码未填写信息封装对象
     * @return 返回 Response 响应封装对象
     */
    @PostMapping("qrCode")
    public Response qrCode(@RequestBody QrCodeVO qrCode) {
        if (!StringUtils.hasText(qrCode.getCode())) {
            throw new BusinessException("未录入未填报二维码人员信息!");
        }
        List<Map<String, Object>> maps = companyService.parseCode(qrCode.getCode());
        if (maps.size() > 0) {
            return Response.valueOfObject(maps);
        } else {
            return Response.valueOfMsg("没有未填报二维码人员");
        }
    }

    /**
     * 查询当前项目组公司人员信息
     *
     * @return 返回响应封装对象
     */
    @GetMapping("getCompanyAll")
    public Response getCompanyAll() {
        return Response.valueOfDate(companyService.queryAll());
    }

    /**
     * 新增一个项目组公司人员信息
     *
     * @param company 公司人员信息对象
     * @return 返回新增后的人员对象信息
     */
    @PostMapping("add")
    public Response addMember(@RequestBody Company company) {
        Company result = companyService.addMember(company);
        return Response.valueOfObject(result);
    }

    /**
     * 更新人员信息
     *
     * @param company 需要更新的人员信息封装实体对象
     * @return 返回响应封装对象
     */
    @PostMapping("update")
    public Response updateMember(@RequestBody Company company){
        Company result = companyService.updateMember(company);
        return Response.valueOfObject(result);
    }

    /**
     * 查询所有人姓名
     *
     * @return 返回响应封装对象
     */
    @GetMapping("queryAllName")
    public Response queryAllName() {
        return Response.valueOfDate(companyService.queryAllName());
    }

    /**
     * 依据查询条件查询对应对象
     *
     * @param condition 查询条件封装对象
     * @return 返回响应封装对象
     */
    @GetMapping("queryByCondition")
    public Response queryByCondition(@RequestBody Company condition) {
        return Response.valueOfObject(companyService.queryAllOfMeetConditions(condition));
    }

    /**
     * 更新人员状态,通过传入的人员ID和需要修改的状态, true: 在职状态; false: 不在职状态;
     *
     * @param company 需要修改人员封装对象
     * @return 反正修改结果响应封装对象
     */
    @PostMapping("updateMemberStatus")
    public Response updateMemberStatus(@RequestBody Company company) {
        companyService.updateMemberStatus(company);
        return Response.valueOfMsg("更新成功");
    }

    /**
     * 按条件分页查询公司人员信息
     *
     * @param company 分页查询条件封装对象
     * @return 返回响应封装对象
     */
    @PostMapping("queryAllMember")
    public Response queryAllMember(@RequestBody CompanyVO company) {
        return Response.builder().object(companyService.queryAll(company)).build();
    }
}
