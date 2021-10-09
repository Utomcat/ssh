package com.ranyikang.ssh.dao;

import com.ranyikang.ssh.entity.SunburstDrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * CLASS_NAME: EchartsDao.java <br/>
 * Description: ECharts 数据库操作接口 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 09
 */
@Repository
public interface EchartsDao extends JpaRepository<SunburstDrinkEntity, String>, PagingAndSortingRepository<SunburstDrinkEntity, String>, JpaSpecificationExecutor<SunburstDrinkEntity> {
}
