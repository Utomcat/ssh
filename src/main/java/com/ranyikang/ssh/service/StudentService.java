package com.ranyikang.ssh.service;

import com.ranyikang.ssh.dao.StudentDao;
import com.ranyikang.ssh.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CLASS_NAME: StudentService.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 学生业务操作类  <br/>
 * @date: 2022-12-23 <br/>
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    public List<Student> testJpaOneToMany() {
        return studentDao.testJpaOneToMany();
    }

}
