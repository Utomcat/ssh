package com.ranyikang.ssh.service;

import com.ranyikang.ssh.dao.EchartsDao;
import com.ranyikang.ssh.entity.ItemStyle;
import com.ranyikang.ssh.entity.SunburstDrinkEntity;
import com.ranyikang.ssh.util.ColorUtils;
import com.ranyikang.ssh.vo.SunburstDrinkVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CLASS_NAME: EchartsServiceImpl.java <br/>
 * Description: ECharts 业务实现类 <br/>
 *
 * @author ranyk <br/>
 * @version V1.0 <br/>
 * @date 2021 - 10 - 08
 */
@Slf4j
@Service("echartsService")
public class EchartsServiceImpl {

    /**
     * 数据库 ECharts 表有关操作接口对象
     */
    @Resource
    private EchartsDao echartsDao;
    /**
     * 零字符串
     */
    private static final String ZERO_STR = "0";


    /**
     * 获取 ECharts 显示数据 sunburst-drink 类型的数据
     *
     * @return 返回对应的数据
     */
    public Object getData() {
        List<SunburstDrinkEntity> allData = echartsDao.findAll();
        List<SunburstDrinkVo> result = new ArrayList<>(allData.isEmpty() ? 16 : allData.size());
        recursiveData(allData, result);
        return result;
    }

    /**
     * 递归填充对应的数据
     *
     * @param allData 需要递归的原数据
     * @param result  递归需要存放的数据集合
     */
    private void recursiveData(List<SunburstDrinkEntity> allData, List<SunburstDrinkVo> result) {
        if (null == allData || allData.isEmpty()) {
            throw new RuntimeException("递归填充数据时,原数据为空!");
        }
        Map<String, List<SunburstDrinkEntity>> groupMap = allData.stream().collect(Collectors.groupingBy(SunburstDrinkEntity::getParentId));
        groupMap.forEach((key, list) -> {
            if (ZERO_STR.equals(key)) {
                result.addAll(conversionEntityList(list));
            }
        });
        groupMap.remove(ZERO_STR);
        traverseData(groupMap, result);
        log.error("当前的result的结果为: {}", result.size());
    }

    private void traverseData(Map<String, List<SunburstDrinkEntity>> map, List<SunburstDrinkVo> result) {
        List<String> tempKey = new ArrayList<>();
        map.forEach((key, list) -> {
            if (null != result && !result.isEmpty()){
                result.forEach(item -> {
                    if (item.getId().equals(key)) {
                        List<SunburstDrinkVo> conversionResult = conversionEntityList(list);
                        item.setChildren(conversionResult);
                        tempKey.add(key);
                    } else {
                        Map<String, List<SunburstDrinkEntity>> subMap = new HashMap<>(1);
                        subMap.put(key, list);
                        traverseData(subMap, item.getChildren());
                    }
                });
            }
        });
        if (!map.isEmpty()){
            tempKey.forEach(map::remove);
            tempKey.clear();
        }
    }

    private List<SunburstDrinkVo> conversionEntityList(List<SunburstDrinkEntity> list) {
        List<SunburstDrinkVo> result = new ArrayList<>(list.size());
        list.forEach(item -> {
            SunburstDrinkVo entityVo = new SunburstDrinkVo();
            BeanUtils.copyProperties(item, entityVo);
            entityVo.setItemStyle(new ItemStyle(ColorUtils.generateHexadecimalColor()));
            if (entityVo.getValue() == 0){
                entityVo.setValue(null);
            }
            result.add(entityVo);
        });
        return result;
    }


}
