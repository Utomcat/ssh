package com.ranyikang.ssh.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * EasyExcel 工具类
 *
 * @author ranYk
 * @version V1.0
 * @date 2022-06-25
 */
@Data
@Slf4j
public class EasyExcelUtils {


    /**
     * 简单的读取 Excel 数据
     *
     * @param fileRootPath 根目录路径
     * @param fileName     文件名
     * @param t            Excel 实体类对象
     * @param <T>          泛型 Excel 对应的实体类
     * @return 返回读取到的 Excel 数据 List 集合
     */
    public static <T> List<T> simpleRead(String fileRootPath, String fileName, T t) {
        AtomicReference<List<T>> result = new AtomicReference<>();
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String filePath = fileRootPath + File.separator + fileName;
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(filePath, t.getClass(), new PageReadListener<>(result::set)).sheet().doRead();
        return result.get();
    }

    /**
     * 简单读取 Excel 数据方法2
     *
     * @param fileRootPath 根目录路径
     * @param fileName     文件名
     * @param t            Excel 实体类对象
     * @param <T>          泛型 Excel 对应的实体类
     * @return 返回读取到的 Excel 数据 List 集合
     */
    public static <T> List<T> simpleRead2(String fileRootPath, String fileName, T t) {
        List<T> cachedDataList = new ArrayList<>();
        // 匿名内部类 不用额外写一个DemoDataListener
        String filePath = fileRootPath + File.separator + fileName;
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, t.getClass(), new ReadListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext context) {
                cachedDataList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet().doRead();

        return cachedDataList;
    }


    public static <T> List<T> complexRead(String fileRootPath, String fileName, int startIndex, T t) {
        List<T> cachedDataList = new ArrayList<>();
        String filePath = fileRootPath + File.separator + fileName;
        EasyExcel.read(filePath, t.getClass(), new AnalysisEventListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext context) {
                int sheetNo = context.readSheetHolder().getSheetNo();
                if (sheetNo == 0) {
                    int index = context.readRowHolder().getRowIndex();
                    if (index >= startIndex) {
                        cachedDataList.add(data);
                    }
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet().doRead();
        return cachedDataList;
    }


}
