package com.ranyikang.ssh.api;

import com.ranyikang.ssh.common.Response;
import com.ranyikang.ssh.entity.User;
import com.ranyikang.ssh.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * CLASS_NAME: UserControllerApi<br/>
 * Description: 用户操作API接口<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 * @date 2021 - 09 - 02 <br/>
 */
@RestController
@RequestMapping("/user/")
public class UserControllerApi {

    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 通过主键ID查询获取User数据对象
     *
     * @param id 需要查询的数据主键ID
     * @return 返回获取到的User数据对象
     */
    @GetMapping("getUserById")
    public Response getUserById(Integer id) {
        return Response.valueOfObject(userServiceImpl.queryUserById(id));
    }

    /**
     * 获取所有的 User 数据
     *
     * @return 返回User数据对象的 List 集合
     */
    @GetMapping("findAllUser")
    public Response findAllUser() {
        return Response.valueOfList(userServiceImpl.findAllUser());
    }



    /**
     * 新增一条数据记录
     *
     * @param user 需要新增的数据 User 对象
     * @return 返回数据操作结果: true: 操作成功;false: 操作失败
     */
    @PostMapping("save")
    public Response save(@RequestBody User user) {
        return Response.valueOfObject(userServiceImpl.save(user));
    }

    /**
     * 编辑已存在的数据记录
     *
     * @param user 需要编辑的数据 User 对象
     * @return 返回数据操作结果: true: 操作成功; false: 操作失败;
     */
    @PostMapping("edit")
    public Response edit(@RequestBody User user) {
        return Response.valueOfObject(userServiceImpl.save(user));
    }

    @PostMapping("deleted")
    public Response deleted(@RequestBody User user) throws Exception {
        try {
            return Response.valueOfObject(userServiceImpl.deleted(user));
        } catch (Exception e) {
            throw new Exception("发生异常,异常信息为: " + e.getMessage());
        }
    }

}
