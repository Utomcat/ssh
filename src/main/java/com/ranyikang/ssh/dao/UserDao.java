package com.ranyikang.ssh.dao;

import com.ranyikang.ssh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CLASS_NAME: UserDao<br/>
 * Description: 用户数据库操作类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 * @date 2021 - 09 - 02 <br/>
 */
@Repository
@SuppressWarnings("all")
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor {

    /**
     * 通过数据主键查询 User 数据
     *
     * @param id 需要查询的数据主键ID
     * @return 返回查询到的数据 User 对象
     */
    @Query("select u from User u where u.id = :id")
    User selectById(@Param("id") Integer id);
}
