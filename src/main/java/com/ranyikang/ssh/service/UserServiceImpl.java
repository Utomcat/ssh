package com.ranyikang.ssh.service;

import com.ranyikang.ssh.dao.UserDao;
import com.ranyikang.ssh.entity.User;
import com.ranyikang.ssh.exception.NullResultException;
import com.ranyikang.ssh.exception.ParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * CLASS_NAME: UserServiceImpl<br/>
 * Description: 用户业务操作类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 * @date 2021 - 09 - 02 <br/>
 */
@Slf4j
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
        if (null == user) {
            return new User();
        } else {
            return user;
        }
    }

    /**
     * 获取所有 User 数据
     *
     * @return 返回查询到的所有 User 数据对象的 List 集合
     */
    public List<User> findAllUser() {
        return userDao.findAll();
    }

    /**
     * 新增一个 User 对象数据
     *
     * @param user 需要新增的 User 数据对象
     * @return 返回数据操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(User user) {
        User resultUser;
        boolean paramIsNull = null == user || (null == user.getId() && null == user.getName());
        if (paramIsNull) {
            return false;
        } else {
            if (null != user.getId() && user.getId() > 0) {
                Optional<User> optionalUser = userDao.findById(user.getId());
                if (optionalUser.isPresent()) {
                    resultUser = userDao.save(user);
                } else {
                    return false;
                }
            } else {
                resultUser = userDao.save(user);
            }
            return resultUser.getId() > 0;
        }
    }

    /**
     * 删除一条 User 数据
     *
     * @param user 需要删除的 User 对象
     * @return 返回操作结果
     * @throws Exception 抛出异常
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleted(User user) throws Exception {
        Integer id = user.getId();
        if (null == id) {
            throw new Exception("需删除数据的ID不存在,不能删除!");
        } else {
            Optional<User> optionalUser = userDao.findById(id);
            if (optionalUser.isPresent()) {
                userDao.updateUserForDeleted(user.getId(), true);
                User u = userDao.selectById(user.getId());
                return u != null && (optionalUser.get().isDeleted() != u.isDeleted());
            } else {
                throw new Exception("依据数据的ID未能查询到数据,不能删除!");
            }
        }
    }

    /**
     * 通过 name 属性值查询 {@link User} 对象
     *
     * @param name 需要查询的 name 属性值
     * @return 返回查询到的数据对象
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean dealMethod(String name) throws ParameterException, NullResultException {
        if (!StringUtils.hasText(name)) {
            log.error("service 传入的参数不存在,请核查!");
            throw new ParameterException("service 传入的参数不存在,请核查!");
        }
        Pageable pageable = Pageable.ofSize(1);
        Page<User> users = userDao.selectUserBySpecifyName(name, pageable);
        if (users == null) {
            log.error("service 未查询到指定条件的结果!");
            throw new NullResultException("service  未查询到指定条件的结果!");
        } else {
            users.getContent();
        }
        userDao.updateUserForDeleted(users.getContent().get(0).getId(), !users.getContent().get(0).isDeleted());
        return true;
    }
}
