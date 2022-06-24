package com.ranyikang.ssh.dao;

import com.ranyikang.ssh.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
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
public interface UserDao extends CrudRepository<User, Integer>, JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 通过数据主键查询 User 数据
     *
     * @param id 需要查询的数据主键ID
     * @return 返回查询到的数据 User 对象
     */
    @Query("select u from User u where u.id = :id")
    User selectById(@Param("id") Integer id);


    /**
     * 逻辑删除 User 数据,注意使用自定义的SQL语句时,此处的表名不能用实体对象,而是需要用对应表名
     *
     * @param id      需要删除的数据 Id
     * @param deleted 删除标志值,统一为 true;
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update sys_user  set deleted = ?2 where id = ?1", nativeQuery = true)
    void updateUserForDeleted(@Param("id") Integer id, @Param("deleted") boolean deleted);


    /**
     * 通过指定的 name 属性查询用户
     *
     * @param name     需要查询的 name 属性值
     * @param pageable 分页查询对象
     * @return 返回查询结果
     */
    @Query("select u from User u where u.name = :name ")
    Page<User> selectUserBySpecifyName(String name, Pageable pageable);


}
