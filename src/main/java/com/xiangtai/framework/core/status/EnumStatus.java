package com.xiangtai.framework.core.status;

/**
 * 状态枚举类型
 *
 * @author ZhangDe
 * @date 2015年9月26日下午5:26:44
 */
public enum EnumStatus implements BaseEnum {
    ON("启用"), OFF("注销");

    EnumStatus(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String getLabel() {
        return this.label;
    }

}
