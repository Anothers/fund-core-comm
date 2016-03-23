package com.xiangtai.framework.core.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 定义annotation类，可以通过该类设置导出相应的属性，标题及排序
 * 用来在对象的get方法上加入的annotation，通过该annotation说明某个属性所对应的标题
 *
 * @author xiangtai
 *         date：2014-4-18
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDataMapper {

    String title();   // 标题名称

    int order();     // 标题顺序

    int width() default 16;   // 单元格宽度

}
