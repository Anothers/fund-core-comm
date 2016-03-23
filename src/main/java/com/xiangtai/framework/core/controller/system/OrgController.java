package com.xiangtai.framework.core.controller.system;

import com.xiangtai.framework.core.annotation.SystemLog;
import com.xiangtai.framework.core.controller.index.BaseController;
import com.xiangtai.framework.core.entity.OrgFormMap;
import com.xiangtai.framework.core.exception.SystemException;
import com.xiangtai.framework.core.mapper.OrgMapper;
import com.xiangtai.framework.core.plugin.PageView;
import com.xiangtai.framework.core.util.*;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xiangtai 2014-11-19
 * @version 3.0v
 *          Controller层，只进行关于页面跳转和调用service
 */
@Controller
@RequestMapping("/org/")
public class OrgController extends BaseController {
    @Inject
    private OrgMapper orgMapper;

    /**
     * @param model
     * @return
     * @throws Exception
     * @SystemLog 在要记录日志的方法上添加将会自动记录到数据库日志中
     */
    @RequestMapping("list")
    @SystemLog(methods = "页面跳转", module = "机构模块")
    public String listUI(Model model) throws Exception {
        model.addAttribute("res", findByRes());
        System.out.println("==========================");
        return Common.BACKGROUND_PATH + "/system/org/list";
    }

    @RequestMapping("list2")
    public String listUI2() throws Exception {
        throw  new SystemException("这是错误页面测试");
    }



    @ResponseBody
    @RequestMapping("findByPage")
    public PageView findByPage(String pageNow,
                               String pageSize, String column, String sort) throws Exception {
        OrgFormMap orgFormMap = getFormMap(OrgFormMap.class);
        String order;
        if (Common.isNotEmpty(column)) {
            order = " order by " + column + " " + sort;
        } else {
            order = " order by id asc";
        }
        orgFormMap.put("$orderby", order);
        orgFormMap = toFormMap(orgFormMap, pageNow, pageSize, orgFormMap.getStr("orderby"));
        pageView.setRecords(orgMapper.findByPage(orgFormMap));
        return pageView;
    }

    @ResponseBody
    @RequestMapping("treelists")
    public OrgFormMap treelists() {
        OrgFormMap orgFormMap = getFormMap(OrgFormMap.class);
        String order = " order by org_id asc";
        orgFormMap.put("$orderby", order);
        List<OrgFormMap> mps = orgMapper.findByNames(orgFormMap);
        List<TreeObject> list = new ArrayList<>();
        for (OrgFormMap map : mps) {
            OrgObjectImpl ts = new OrgObjectImpl();
            Common.flushObject(ts, map);
            list.add(ts);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
        orgFormMap = new OrgFormMap();
        orgFormMap.put("treelists", ns);
        return orgFormMap;
    }

    /**
     * 跳转到新增界面
     *
     * @return
     */
    @RequestMapping("addUI")
    public String addUI() {
        return Common.BACKGROUND_PATH + "/system/org/add";
    }

    @ResponseBody
    @RequestMapping("orglists")
    public List<TreeObject> orglists() throws Exception {
        OrgFormMap orgFormMap = getFormMap(OrgFormMap.class);
        List<OrgFormMap> mps = orgMapper.findByWhere(orgFormMap);
        List<TreeObject> list = new ArrayList<>();
        for (OrgFormMap map : mps) {
            OrgObjectImpl ts = new OrgObjectImpl();
            Common.flushObject(ts, map);
            list.add(ts);
        }
        TreeUtil treeUtil = new TreeUtil();
        return treeUtil.getChildTreeObjects(list, 0, "　");
    }

    /**
     * 验证机构是否存在
     *
     * @return
     */
    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist() {
        OrgFormMap orgFormMap = getFormMap(OrgFormMap.class);
        List<OrgFormMap> r = orgMapper.findByNames(orgFormMap);
        return r.size() == 0;
    }

    /**
     * 验证机构是否存在编辑方法
     *
     * @param org_name
     * @param org_code
     * @return
     */
    @RequestMapping("isExist2")
    @ResponseBody
    public boolean isExist2(String org_name, String org_code, String h_org_name, String h_org_code) {
        Map<String,String> reMap = new HashMap<>();
        reMap.put("org_name",org_name);
        reMap.put("org_code",org_code);
        reMap.put("h_org_name",h_org_name);
        reMap.put("h_org_code",h_org_code);
        List<OrgFormMap> r = orgMapper.isExist2(reMap);
        return r.size() == 0;
    }

    /**
     * 添加机构
     *
     * @return Map
     * @throws Exception
     */
    @RequestMapping("addEntity")
    @ResponseBody
    @Transactional(readOnly = false)//需要事务操作必须加入此注解
    @SystemLog(module = "机构管理", methods = "添加机构")//凡需要处理业务逻辑的.都需要记录操作日志
    public String addEntity() throws Exception {
        OrgFormMap orgFormMap = getFormMap(OrgFormMap.class);
        if ("2".equals(orgFormMap.get("type"))) {
            orgFormMap.put("description", Common.htmltoString(orgFormMap.get("description") + ""));
        }
        orgMapper.addEntity(orgFormMap);
        return "success";
    }

    /**
     * 跳转到修改界面
     *
     * @param model
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model) {
        String org_id = getPara("org_id");
        if(Common.isNotEmpty(org_id)){
            model.addAttribute("org", orgMapper.findbyFrist("org_id", org_id, OrgFormMap.class));
        }
        return Common.BACKGROUND_PATH + "/system/org/edit";
    }

    /**
     * 更新菜单
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("eidtEntity")
    @Transactional(readOnly=false)//需要事务操作必须加入此注解
    @SystemLog(module="机构管理",methods="修改机构")//凡需要处理业务逻辑的.都需要记录操作日志
    public String eidtEntity() throws Exception {
        OrgFormMap resFormMap = getFormMap(OrgFormMap.class);
        orgMapper.editEntity(resFormMap);
        return "success";
    }
}