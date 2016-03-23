package com.xiangtai.framework.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 */
public class TreeUtil {


    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<TreeObject> getChildTreeObjects(List<TreeObject> list, int parentId) {
        List<TreeObject> returnList = new ArrayList<>();
        for (TreeObject t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t    TreeObject
     * @author xiangtai
     * Email: mmm333zzz520@163.com
     * @date 2013-12-4 下午7:27:30
     */
    private void recursionFn(List<TreeObject> list, TreeObject t) {
        List<TreeObject> childList = getChildList(list, t);// 得到子节点列表
        t.setChildren(childList);
        for (TreeObject tChild : childList) {
            if (hasChild(list, tChild)) {// 判断是否有子节点
                //returnList.add(TreeObject);
                for (TreeObject n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    // 得到子节点列表
    private List<TreeObject> getChildList(List<TreeObject> list, TreeObject t) {

        List<TreeObject> tlist = new ArrayList<>();
        for (TreeObject n : list) {
            if (Objects.equals(n.getParentId(), t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<TreeObject> returnList = new ArrayList<>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list   分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<TreeObject> getChildTreeObjects(List<TreeObject> list, int typeId, String prefix) {
        if (list == null) return null;
        for (TreeObject node : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId) {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }

    private void recursionFn(List<TreeObject> list, TreeObject node, String p) {
        List<TreeObject> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node);
            for (TreeObject n : childList) {
                n.setName(p + n.getName());
                recursionFn(list, n, p + p);
            }
        } else {
            returnList.add(node);
        }
    }

    // 判断是否有子节点
    private boolean hasChild(List<TreeObject> list, TreeObject t) {
        return getChildList(list, t).size() > 0;
    }

    // 本地模拟数据测试
    public void main(String[] args) {
        /*long start = System.currentTimeMillis();
        List<TreeObject> TreeObjectList = new ArrayList<TreeObject>();
		
		TreeObjectUtil mt = new TreeObjectUtil();
		List<TreeObject> ns=mt.getChildTreeObjects(TreeObjectList,0);
		for (TreeObject m : ns) {
			System.out.println(m.getName());
			System.out.println(m.getChildren());
		}
		long end = System.currentTimeMillis();
		System.out.println("用时:" + (end - start) + "ms");*/
    }

}
