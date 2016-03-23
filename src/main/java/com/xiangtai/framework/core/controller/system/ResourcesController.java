package com.xiangtai.framework.core.controller.system;

import com.xiangtai.framework.core.annotation.SystemLog;
import com.xiangtai.framework.core.controller.index.BaseController;
import com.xiangtai.framework.core.entity.*;
import com.xiangtai.framework.core.mapper.ResourcesMapper;
import com.xiangtai.framework.core.util.Common;
import com.xiangtai.framework.core.util.TreeObject;
import com.xiangtai.framework.core.util.TreeObjectImpl;
import com.xiangtai.framework.core.util.TreeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangtai 2014-11-19
 * @version 3.0v
 */
@Controller
@RequestMapping("/resources/")
public class ResourcesController extends BaseController {
    @Inject
    private ResourcesMapper resourcesMapper;

    /**
     */
    @ResponseBody
    @RequestMapping("treelists")
    public ResFormMap findByPage() {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        String order = " order by level asc";
        resFormMap.put("$orderby", order);
        List<ResFormMap> mps = resourcesMapper.findByNames(resFormMap);
        List<TreeObject> list = new ArrayList<>();
        for (ResFormMap map : mps) {
            TreeObjectImpl ts = new TreeObjectImpl();
            Common.flushObject(ts, map);
            list.add(ts);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
        resFormMap = new ResFormMap();
        resFormMap.put("treelists", ns);
        return resFormMap;
    }

    @ResponseBody
    @RequestMapping("reslists")
    public List<TreeObject> reslists() throws Exception {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        List<ResFormMap> mps = resourcesMapper.findByWhere(resFormMap);
        List<TreeObject> list = new ArrayList<>();
        for (ResFormMap map : mps) {
            TreeObjectImpl ts = new TreeObjectImpl();
            Common.flushObject(ts, map);
            list.add(ts);
        }
        TreeUtil treeUtil = new TreeUtil();
        return treeUtil.getChildTreeObjects(list, 0, "　");
    }

    /**
     * @param model 存放返回界面的model
     */
    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/system/resources/list";
    }

    /**
     *
     * 修改页面跳转
     */
    @RequestMapping("editUI")
    public String editUI(Model model) {
        String id = getPara("id");
        if (Common.isNotEmpty(id)) {
            model.addAttribute("resources", resourcesMapper.findbyFrist("id", id, ResFormMap.class));
        }
        return Common.BACKGROUND_PATH + "/system/resources/edit";
    }

    /**
     * 跳转到新增界面
     *
     */
    @RequestMapping("addUI")
    public String addUI() {
        return Common.BACKGROUND_PATH + "/system/resources/add";
    }

    /**
     * 权限分配页面
     *
     * @author xiangtai Email：mmm333zzz520@163.com date：2014-4-14
     */
    @RequestMapping("permissions")
    public String permissions(Model model) {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        List<ResFormMap> mps = resourcesMapper.findByWhere(resFormMap);
        List<TreeObject> list = new ArrayList<>();
        for (ResFormMap map : mps) {
            TreeObjectImpl ts = new TreeObjectImpl();
            Common.flushObject(ts, map);
            list.add(ts);
        }
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
        model.addAttribute("permissions", ns);
        return Common.BACKGROUND_PATH + "/system/resources/permissions";
    }

    /**
     * 添加菜单
     *
     * @return Map
     * @throws Exception
     */
    @RequestMapping("addEntity")
    @ResponseBody
    @Transactional(readOnly = false)//需要事务操作必须加入此注解
    @SystemLog(module = "系统管理", methods = "资源管理-新增资源")//凡需要处理业务逻辑的.都需要记录操作日志
    public String addEntity() throws Exception {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        if ("2".equals(resFormMap.get("type"))) {
            resFormMap.put("description", Common.htmltoString(resFormMap.get("description") + ""));
        }
        Object o = resFormMap.get("ishide");
        if (null == o) {
            resFormMap.set("ishide", "0");
        }

        resourcesMapper.addEntity(resFormMap);
        return "success";
    }

    /**
     * 更新菜单
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("editEntity")
    @Transactional(readOnly = false)//需要事务操作必须加入此注解
    @SystemLog(module = "系统管理", methods = "资源管理-修改资源")//凡需要处理业务逻辑的.都需要记录操作日志
    public String editEntity() throws Exception {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        if ("2".equals(resFormMap.get("type"))) {
            resFormMap.put("description", Common.htmltoString(resFormMap.get("description") + ""));
        }
        Object o = resFormMap.get("ishide");
        if (null == o) {
            resFormMap.set("ishide", "0");
        }
        resourcesMapper.editEntity(resFormMap);
        return "success";
    }

    /**
     * 根据ID删除菜单
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("deleteEntity")
    @SystemLog(module = "系统管理", methods = "资源管理-删除资源")//凡需要处理业务逻辑的.都需要记录操作日志
    public String deleteEntity() throws Exception {
        String[] ids = getParaValues("ids");
        for (String id : ids) {
            resourcesMapper.deleteByAttribute("id", id, ResFormMap.class);
        }
        return "success";
    }

    @RequestMapping("sortUpdate")
    @ResponseBody
    @Transactional(readOnly = false)//需要事务操作必须加入此注解
    public String sortUpdate(Params params) throws Exception {
        List<String> ids = params.getId();
        List<String> es = params.getRowId();
        List<ResFormMap> maps = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            ResFormMap map = new ResFormMap();
            map.put("id", ids.get(i));
            map.put("level", es.get(i));
            maps.add(map);
        }
        resourcesMapper.updateSortOrder(maps);
        return "success";
    }

    @ResponseBody
    @RequestMapping("findRes")
    public List<ResFormMap> findUserRes() {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        return resourcesMapper.findRes2(resFormMap);
    }

    @ResponseBody
    @RequestMapping("addUserRes")
    @Transactional(readOnly = false)//需要事务操作必须加入此注解
    @SystemLog(module = "系统管理", methods = "用户管理/组管理-修改权限")//凡需要处理业务逻辑的.都需要记录操作日志
    public String addUserRes() throws Exception {
        String userId = "";
        String u = getPara("userId");
        String g = getPara("roleId");

        if (null != u && !Common.isEmpty(u)) {
            userId = u;
        } else if (null != g && !Common.isEmpty(g)) {
            List<UserGroupsFormMap> gs = resourcesMapper.findByAttribute("roleId", g, UserGroupsFormMap.class);
            for (UserGroupsFormMap ug : gs) {
                userId += ug.get("userId") + ",";
            }
        }
        //删除角色类资源权限数据
        if (null != g && !Common.isEmpty(g)) {
            resourcesMapper.deleteByRoleIdType(g);


            String[] s = getParaValues("resId[]");
            String[] r = getParaValues("selectAuthority[]");

            List<ResUserFormMap> resUserFormMaps = new ArrayList<>();
            if (null != s && s.length >= 0) {
                for (int i = 0; i < s.length; i++) {
                    ResUserFormMap resUserFormMap = new ResUserFormMap();
                    resUserFormMap.put("resId", s[i]);
                    resUserFormMap.put("roleId", g);
                    resUserFormMap.put("type", "01");

                    if (i < r.length)
                        resUserFormMap.put("dataAuthority", r[i]);
                    else
                        resUserFormMap.put("dataAuthority", "00");
                    resUserFormMaps.add(resUserFormMap);
                }

                resourcesMapper.batchSave(resUserFormMaps);
            }
        }
        userId = Common.trimComma(userId);
//        if (Common.isEmpty(userId)) {
//            return "分配失败,该角色下没有用户!";
//        }


        String[] users = userId.split(",");
        for (String uid : users) {
            ResUserFormMap resUserFormMap2 = new ResUserFormMap();
            resUserFormMap2.put("userId",uid);
            resUserFormMap2.put("roleId", g);
            String[] s = getParaValues("resId[]");
            String[] r = getParaValues("selectAuthority[]");
            if ((null != s && s.length >= 0) && !users.equals("")) {
                resourcesMapper.deleteByUserIdRoleId(resUserFormMap2);
                List<ResUserFormMap> resUserFormMaps = new ArrayList<>();

                for (int i = 0; i < s.length; i++) {
                    ResUserFormMap resUserFormMap = new ResUserFormMap();
                    resUserFormMap.put("resId", s[i]);
                    resUserFormMap.put("userId", uid);
                    resUserFormMap.put("roleId", g);
                    resUserFormMap.put("type", "02");
                    if (i < r.length)
                        resUserFormMap.put("dataAuthority", r[i]);
                    else
                        resUserFormMap.put("dataAuthority", "00");
                    resUserFormMaps.add(resUserFormMap);
                }
                resourcesMapper.batchSave(resUserFormMaps);
            }
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("findByButtom")
    public List<ButtomFormMap> findByButtom() {
        return resourcesMapper.findByWhere(new ButtomFormMap());
    }

    /**
     * 验证菜单是否存在
     *
     * @return
     */
    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist() {
        ResFormMap resFormMap = getFormMap(ResFormMap.class);
        List<ResFormMap> r = resourcesMapper.findByNames(resFormMap);
        return r.size() == 0;
    }
}