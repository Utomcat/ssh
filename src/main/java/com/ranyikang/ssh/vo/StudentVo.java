package com.ranyikang.ssh.vo;

import com.ranyikang.ssh.entity.Course;
import com.ranyikang.ssh.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;


/**
 * CLASS_NAME: StudentVo.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: Student Vo  <br/>
 * @date: 2022-12-23 <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudentVo extends Student {



}
