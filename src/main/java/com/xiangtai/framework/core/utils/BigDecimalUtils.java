package com.xiangtai.framework.core.utils;

import java.math.BigDecimal;
import java.util.Map;

public class BigDecimalUtils {
    private static final int DEF_DIV_SCALE = 10;
    private static final int DEF_DIV_SCALE_2 = 2;
    private static final int DEF_DIV_SCALE_3 = 3;
    private static final int DEF_DIV_SCALE_4 = 4;
    private static final int DEF_DIV_SCALE_6 = 6;
    private static final int DEF_DIV_SCALE_8 = 8;
    private static final int DEF_DIV_SCALE_10 = 10;
    private static final int DEF_DIV_SCALE_12 = 12;

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }


    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal subtract(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static String mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static String div(String v1, String v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度 与div的区别是不进行四舍五入的计算，只按照scale截取
     * 只保留两位小数（不进行四舍五入）
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div2(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, DEF_DIV_SCALE_2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static String div2(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, DEF_DIV_SCALE_2, BigDecimal.ROUND_DOWN).toString();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度 与div的区别是不进行四舍五入的计算，只按照scale截取
     * 只保留四位小数（不进行四舍五入）
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div4(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, DEF_DIV_SCALE_4, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static String div4(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, DEF_DIV_SCALE_4, BigDecimal.ROUND_DOWN).toString();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 方法说明：提供精确的小数位四舍五入处理
     * 创建人：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2016-3-15 上午9:48:56
     */
    public static BigDecimal round(BigDecimal b, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 方法说明：保留两位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:00
     * 参数列表：
     */
    public static BigDecimal divide2(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 方法说明：保留三位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:00
     * 参数列表：
     */
    public static BigDecimal divide3(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_3, BigDecimal.ROUND_DOWN);
    }

    /**
     * 方法说明：保留六位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:13
     * 参数列表：
     */
    public static BigDecimal divide6(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_6, BigDecimal.ROUND_DOWN);
    }

    /**
     * 方法说明：保留八位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:13
     * 参数列表：
     */
    public static BigDecimal divide8(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_8, BigDecimal.ROUND_DOWN);
    }

    /**
     * 方法说明：保留10位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:13
     * 参数列表：
     */
    public static BigDecimal divide10(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_10, BigDecimal.ROUND_DOWN);
    }

    /**
     * 方法说明：保留12位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:13
     * 参数列表：
     */
    public static BigDecimal divide12(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_12, BigDecimal.ROUND_DOWN);
    }


    /**
     * 方法说明：保留四位小数
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2014-10-15 上午10:12:13
     * 参数列表：
     */
    public static BigDecimal divide4(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEF_DIV_SCALE_4, BigDecimal.ROUND_DOWN);
    }


    /**
     * 方法说明：两数相除，向上取整
     * 创建者：范兴乾
     * 返回类型：int
     * 创建时间：2014-10-15 下午4:01:11
     * 参数列表：
     */
    public static int divideUp(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, 0, BigDecimal.ROUND_UP).intValue();
    }

    /**
     * 方法说明：Map对象中转换成BigDecimal
     * 创建者：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2015-1-28 上午9:44:49
     * 参数列表：
     */
    public static BigDecimal strToBigDecimal(String filedStr, Map<String, Object> params) {
        String bigVal = (String) params.get(filedStr);
        if (bigVal != null) {
            return new BigDecimal(bigVal);
        }
        return new BigDecimal(0);
    }

    public static void main(String[] args) {
        BigDecimal v1 = new BigDecimal(34.2);
        BigDecimal v2 = new BigDecimal(1);
        BigDecimal temp = div(v1, v2, 0);
        System.out.println(temp);
    }

    /**
     * 方法说明：Double转换成BigDecimal
     * 创建人：范兴乾
     * 返回类型：BigDecimal
     * 创建时间：2016-3-6 上午11:40:15
     */
    public static BigDecimal dToBigDecimal(Map<String, Object> map, String keyStr) {
        Double valD = (Double) map.get(keyStr);
        BigDecimal val = new BigDecimal(valD);
        return val;
    }


}
