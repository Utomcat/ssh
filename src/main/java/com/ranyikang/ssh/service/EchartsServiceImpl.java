package com.ranyikang.ssh.service;

import com.ranyikang.ssh.dao.EchartsDao;
import com.ranyikang.ssh.entity.ItemStyle;
import com.ranyikang.ssh.entity.SunburstDrinkEntity;
import com.ranyikang.ssh.util.ColorUtils;
import com.ranyikang.ssh.vo.SunburstDrinkVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * Integer 类型的零值
     */
    private static final Integer ZERO_INT = 0;
    /**
     * 换行字符串
     */
    private static final String WRAP_STR = "\\n";

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
        log.error("当前 groupMap 的结果长度为: {}", groupMap.size());
        log.error("当前的result的结果为: {}", result.size());
    }

    /**
     * 遍历数据,将查询结果按照树形结构组装到返回结果 List 集合中
     *
     * @param map    查询结果分组后的 Map 集合,分组条件为按 parentId 属性值分组
     * @param result 返回的结果数据 List 集合
     */
    private void traverseData(Map<String, List<SunburstDrinkEntity>> map, List<SunburstDrinkVo> result) {
        List<String> tempKey = new ArrayList<>();
        traverseData(map, result, tempKey);
        if (!map.isEmpty()) {
            tempKey.forEach(map::remove);
            tempKey.clear();
        }
        if (map.size() > 0) {
            traverseData(map, result);
        }
    }

    /**
     * 遍历数据,递归循环调用方法,将结果数据按照树形结构放入返回结果 List 集合中,同时将已经遍历过的数据放入 Map 需要移除的 Key List 集合中
     *
     * @param map     查询结果分组后的 Map 集合,分组条件为按 parentId 属性值分组
     * @param result  返回结果数据 List 集合
     * @param tempKey 需要移除 Map 数据的 key 值 List 集合
     */
    private void traverseData(Map<String, List<SunburstDrinkEntity>> map, List<SunburstDrinkVo> result, List<String> tempKey) {
        map.forEach((key, list) -> {
            if (null != result && !result.isEmpty()) {
                result.forEach(item -> {
                    if (item.getId().equals(key)) {
                        List<SunburstDrinkVo> conversionResult = conversionEntityList(list);
                        item.setChildren(conversionResult);
                        tempKey.add(key);
                    } else {
                        Map<String, List<SunburstDrinkEntity>> subMap = new HashMap<>(1);
                        subMap.put(key, list);
                        traverseData(subMap, item.getChildren(), tempKey);
                    }
                });
            }
        });
    }

    /**
     * 将数据库 Entity 实体对象 List 集合,转换为 VO 对象 List 集合
     *
     * @param list Entity 实体对象 List 集合
     * @return 返回转换后的 VO 对象 List 集合
     */
    private List<SunburstDrinkVo> conversionEntityList(List<SunburstDrinkEntity> list) {
        List<SunburstDrinkVo> result = new ArrayList<>(list.size());
        list.forEach(item -> {
            SunburstDrinkVo entityVo = new SunburstDrinkVo();
            BeanUtils.copyProperties(item, entityVo);
            entityVo.setItemStyle(new ItemStyle(ColorUtils.generateHexadecimalColor()));
            if (ZERO_INT.equals(entityVo.getValue())) {
                entityVo.setValue(null);
            }
            String name = entityVo.getName();
            if (StringUtils.hasText(name) && name.contains(WRAP_STR)) {
                entityVo.setName(name.replace(WRAP_STR, "\n"));
            }
            result.add(entityVo);
        });
        return result;
    }


}
