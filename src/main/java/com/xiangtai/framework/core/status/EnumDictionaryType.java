package com.xiangtai.framework.core.status;

/**
 * 字典类型枚举类型 dir 目录，data 数据
 *
 * @author ZhangDe
 * @date 2015年9月26日下午4:45:06
 */
public enum EnumDictionaryType implements BaseEnum {
    DIR("目录"), DATA("数据");

    private String label;

    EnumDictionaryType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
