package com.ranyikang.ssh.service;

import com.ranyikang.ssh.dao.UserDao;
import com.ranyikang.ssh.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * CLASS_NAME: UserServic<br/>
 * Description: 用户业务操作类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 * @date 2021 - 09 - 02 <br/>
 */
@Service("userServiceImpl")
public class UserServiceImpl {

    @Resource
    private UserDao userDao;

    /**
     * 通过数据ID查询User数据
     *
     * @param id 需要查询的数据ID
     * @return 返回获取到的User数据对象
     */
    public User queryUserById(Integer id) {
        User user = userDao.selectById(id);
        if (null == user ) {
            return new User();
        } else {
            return user;
        }
    }

    /**
     * 新增一个 User 对象数据
     *
     * @param user 需要新增的 User 数据对象
     * @return 返回数据操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(User user) {
        user.setId(LocalDateTime.now().getNano());
        int result =  userDao.insert(user);
        return result > 0;
    }
}
