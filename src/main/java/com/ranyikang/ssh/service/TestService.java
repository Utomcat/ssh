package com.ranyikang.ssh.service;

import com.alibaba.fastjson2.JSON;
import com.ranyikang.ssh.entity.Student;
import com.ranyikang.ssh.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * CLASS_NAME: TestService.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 测试业务接口类  <br/>
 * @date: 2022-07-14 <br/>
 */
@Slf4j
@Service
public class TestService {

    /**
     * 定义全局变量,并初始化为 0
     */
    BigDecimal globalOne = BigDecimal.ZERO;
    /**
     * 最大循环次数
     */
    private static final int MAX_CYCLES = 10;
    /**
     * 初始容量
     */
    private static final int INITIAL_CAPACITY = 16;

    /**
     * 学生业务类
     */
    private StudentService studentService;

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 测试方法0,用来测试全局变量
     */
    public void testMethodZero() {
        for (int i = 0; i < MAX_CYCLES; i++) {
            globalOne = globalOne.add(BigDecimal.valueOf(i));
        }
        log.info("本次循环结果 {}", globalOne.intValue());
    }

    /**
     * 形实参数测试方法
     *
     * @return 返回 Map&lt;String,Object&gt; 集合
     */
    public Map<String, Object> testFormAndRealParam() {
        List<String> list = new ArrayList<>(16);
        List<String> result = listHandle(list);
        Map<String, Object> map = new HashMap<>(4);
        map.put("list", list);
        map.put("list 地址", System.identityHashCode(list));
        map.put("result", result);
        map.put("result 地址", System.identityHashCode(result));
        return map;
    }

    public Object testJpaOneToMany() {

        return studentService.testJpaOneToMany();
    }

    public int testSave(Student student) {
        Student redisCacheStudent = JSON.parseObject(redisTemplate.opsForValue().get(student.getName()), Student.class);
        if (redisCacheStudent != null && redisCacheStudent.getName().equals(student.getName())) {
            throw new BusinessException("当前正在保存提交的用户,请等待保存后再进行操作!");
        } else {
            redisTemplate.opsForValue().set(student.getName(), JSON.toJSONString(student), 120, TimeUnit.SECONDS);
            int saveResult = studentService.saveStudent(student);
            Boolean delete = redisTemplate.delete(student.getName());
            if (Boolean.TRUE.equals(delete)) {
                log.info("移除 redis 中的缓存 key 成功,删除结果为 {}", true);
                return saveResult;
            } else {
                throw new BusinessException("移除 redis 中的缓存 key 失败,请重新尝试保存");
            }

        }
    }


    /**
     * 处理 List 集合
     *
     * @param list 需要处理的 List 集合
     * @return 返回处理好的 List 集合
     */
    private List<String> listHandle(List<String> list) {
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
