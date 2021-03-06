package com.xiangtai.framework.core.util;

import org.apache.ibatis.io.Resources;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 对属性文件操作的工具类
 * 获取，新增，修改
 * 注意：	以下方法读取属性文件会缓存问题,在修改属性文件时，不起作用，
 * 　InputStream in = PropertiesUtils.class.getResourceAsStream("/config.properties");
 * 　解决办法：
 * 　String savePath = PropertiesUtils.class.getResource("/config.properties").getPath();
 *
 * @author xiangtai
 *         2014-11-19
 * @version 3.0v
 */
public class PropertiesUtils {
    /**
     * 获取属性文件的数据 根据key获取值
     *
     * @param key
     * @return
     */
    public static String findPropertiesKey(String key) {

        try {
            Properties prop = getProperties();
            return prop != null ? prop.getProperty(key) : null;
        } catch (Exception e) {
            return "";
        }

    }

    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream in = PropertiesUtils.class
                .getResourceAsStream("/config.properties");
        try {
            prop.load(in);
            for (Entry<Object, Object> e : prop.entrySet()) {
                System.err.println((e.getKey().toString() + "" + e.getValue()
                        .toString()));
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * 返回　Properties
     *
     * @param
     * @return
     */
    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            Reader reader = Resources.getResourceAsReader("/config.properties");
            prop.load(reader);
        } catch (Exception e) {
            return null;
        }
        return prop;
    }

    public static Properties getjdbcProperties() {
        Properties prop = new Properties();
        try {
            Reader reader = Resources.getResourceAsReader("/jdbc.properties");
            prop.load(reader);
        } catch (Exception e) {
            return null;
        }
        return prop;
    }

    /**
     * 写入properties信息
     *
     * @param key   名称
     * @param value 值
     */
    public static void modifyProperties(String key, String value) {
        try {
            // 从输入流中读取属性列表（键和元素对）
            Properties prop = getProperties();
            if (prop != null) {
                prop.setProperty(key, value);
            }
            String path = PropertiesUtils.class.getResource("/config.properties").getPath();
            FileOutputStream outputFile = new FileOutputStream(path);
            if (prop != null) {
                prop.store(outputFile, "modify");
            }
            outputFile.close();
            outputFile.flush();
        } catch (Exception ignored) {
        }
    }
}
