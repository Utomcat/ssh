package com.ranyikang.ssh.dao;

import com.ranyikang.ssh.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * CLASS_NAME: CompanyDao.java <br/>
 *
 * @author ranyk           <br/>
 * @version V1.0           <br/>
 * @description: 公司人员信息数据库操作接口  <br/>
 * @date: 2022-07-07 <br/>
 */
@Repository
public interface CompanyDao extends CrudRepository<Company, Integer>, JpaRepository<Company, Integer>, PagingAndSortingRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

    /**
     * 查询当前在项目组的人员信息
     *
     * @param onTheJob 指定当前在岗状态
     * @return 返回查询结果 List 集合
     */
    List<Company> findByOnTheJobIs(boolean onTheJob);

    /**
     * 查询当前项目组指定在岗状态的所有人员姓名
     *
     * @param onTheJob 指定当前的在岗状态
     * @return 返回查询结果 List 集合
     */
    @Query("select name from Company where onTheJob = :onTheJob")
    List<String> findNameByOnTheJobIs(boolean onTheJob);
}
