package com.ranyikang.ssh.dao;

import com.ranyikang.ssh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CLASS_NAME: UserDao<br/>
 * Description: 用户数据库操作类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 * @date 2021 - 09 - 02 <br/>
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 通过数据主键查询 User 数据
     *
     * @param id 需要查询的数据主键ID
     * @return 返回查询到的数据 User 对象
     */
    @Query("select u from User u where u.id = :id")
    User selectById(@Param("id") Integer id);

    /**
     * 新增一条数据
     *
     * @param user 需要新增数据的 User 对象
     * @return 返回数据操作的影响行数
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "insert into User(id,name) value(?,?) ", nativeQuery = true)
    int insert(User user);
}
