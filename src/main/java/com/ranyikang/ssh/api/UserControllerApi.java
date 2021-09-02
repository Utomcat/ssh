package com.ranyikang.ssh.api;

import com.ranyikang.ssh.entity.User;
import com.ranyikang.ssh.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping("getUserById")
    public User getUserById(Integer id){
        return userServiceImpl.queryUserById(id);
    }

    @PostMapping("addUser")
    public boolean addUser(@RequestBody User user){
        return userServiceImpl.save(user);
    }

}
