package com.xiangtai.framework.core.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xiangtai.framework.core.entity.DictionaryFormMap;
import com.xiangtai.framework.core.mapper.DictionaryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Zhangde
 * @ClassName: SelectHander.java
 * @Description: 自定义下拉框
 * @date 2016年3月13日下午1:43:04
 */
public class SelectHander extends TagSupport {
    /**
     *
     */
    private static final long serialVersionUID = -1714330894742881426L;


    public String getCode_type() {
        return code_type;
    }

    /**
     * 类型
     */
    private String code_type;

    /**
     * HTML class
     */
    private String className;

    /**
     * HTML id
     */
    private String id;

    /**
     * HTML name
     */
    private String name;

    /**
     * 是否必输 ：就是 是否带有请选择还是默认选中第一个
     */
    private String required;

    /**
     * 值 支持 EL表达式
     */
    private String value;

    /**
     * 是否将值反显为label形式
     */
    private boolean isLabel;

    /**
     * 不可编辑
     */
    private boolean disabled;

    /**
     * 不在列表处显示
     * except = "1,2,3,4,5"
     */
    private String except;


    @Override
    public int doEndTag() throws JspException {

        WebApplicationContext watx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());

        DictionaryMapper dictionaryMapper = watx.getBean(DictionaryMapper.class);

        StringBuilder htmlBuff = new StringBuilder();
        //业务代码
        DictionaryFormMap dictionFormMap = new DictionaryFormMap();
        dictionFormMap.put("code_type", code_type);
        List<DictionaryFormMap> dictionaryList = dictionaryMapper.searchForCodeType(dictionFormMap);
        // 反显value
        if (isLabel) {
            if (StringUtils.isBlank(value)) {
                htmlBuff.append("<span></span>");
            } else {
                if (null != dictionaryList && dictionaryList.size() > 0) {
                    Iterator<DictionaryFormMap> ite = dictionaryList.iterator();
                    int i = 0;
                    while (ite.hasNext()) {
                        DictionaryFormMap dictionaryFormMap = ite.next();
                        String key = dictionaryFormMap.getStr("code");
                        if (key.equals(value)) {
                            htmlBuff.append("<span>").append(dictionaryFormMap.getStr("code_name")).append("<span>");
                            i++;
                            continue;
                        }
                    }
                    if (i == 0) htmlBuff.append("<span></span>");
                } else htmlBuff.append("<span></span>");
            }

        }
        // 正常下拉
        else {
            htmlBuff.append("<select");

            //是否必须
            if (StringUtils.isNotBlank(required)) {
                if (required.equals("required")) {
                    htmlBuff.append("  required");
                }
            }

            if (StringUtils.isNotBlank(className)) {

                htmlBuff.append(" class='").append(className).append("'");
            } else htmlBuff.append(" class='form-control'");

            if (StringUtils.isNotBlank(id)) {

                htmlBuff.append(" id='").append(id).append("'");
            }

            if (StringUtils.isNotBlank(name)) {

                htmlBuff.append(" name='").append(name).append("'");
            }

            if (disabled) {

                htmlBuff.append(" disabled='").append(disabled).append("'style='background-color:#F0F0F0'");
            }

            htmlBuff.append(">");

            if (StringUtils.isBlank(value)) {
                htmlBuff.append("<option value=''>请选择</option>");
            }

            if (null != dictionaryList && dictionaryList.size() > 0) {

                Iterator<DictionaryFormMap> ite = dictionaryList.iterator();

                List<String> excepts = new ArrayList<>();

                if (StringUtils.isNotBlank(except)) {
                    String[] args = except.split(",");

                    excepts = Arrays.asList(args);
                }

                while (ite.hasNext()) {

                    DictionaryFormMap dictionaryFormMap = ite.next();
                    String key = dictionaryFormMap.getStr("code");

                    if (excepts.contains(key)) {
                        continue;
                    }
                    if (key.equals(value)) {

                        htmlBuff.append("<option value='").append(key).append("' selected='selected' >").append(dictionaryFormMap.getStr("code_name")).append("</option>");

                        continue;
                    }

                    htmlBuff.append("<option value='").append(key).append("'>").append(dictionaryFormMap.getStr("code_name")).append("</option>");

                }
            }

            htmlBuff.append("</select>");
        }


        try {

            JspWriter out = pageContext.getOut();

            out.println(htmlBuff.toString());

            out.flush();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return TagSupport.EVAL_PAGE;
    }

    public void setIsLabel(boolean isLabel) {
        this.isLabel = isLabel;
    }

    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }


    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setExcept(String except) {
        this.except = except;
    }


}