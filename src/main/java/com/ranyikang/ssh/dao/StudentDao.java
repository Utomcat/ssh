package com.ranyikang.ssh.dao;

import com.ranyikang.ssh.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CLASS_NAME: StudentDao.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 学生类数据库操作类  <br/>
 * @date: 2022-12-23 <br/>
 */
@Repository
public interface StudentDao extends CrudRepository<Student, Integer>,
        JpaRepository<Student, Integer>,
        PagingAndSortingRepository<Student, Integer>,
        JpaSpecificationExecutor<Student> {

    /**
     * 查询每个学生以及学生对应选择的课程
     *
     * @return List 集合,单个实体对象参见 {@link Student}
     */
    @Query("select distinct u from Student u left join CourseStudent  cs on u.id = cs.studentId left join Course  c on  cs.courseId = c.id")
    List<Student> testJpaOneToMany();

}
