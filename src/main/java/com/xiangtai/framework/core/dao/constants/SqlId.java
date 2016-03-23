package com.xiangtai.framework.core.dao.constants;

/**
 * Mybatis Sql脚本的ID名称
 *
 * @author Jiangbin
 * @date 2014-3-7下午10:27:42
 */
public interface SqlId {
    String SQL_SELECT_COUNT = "selectCount";
    String SQL_SELECT = "select";
    String SQL_SELECT_BY_ID = "selectById";
    String SQL_UPDATE_BY_ID = "updateById";
    String SQL_UPDATE_BY_ID_SELECTIVE = "updateByIdSelective";
    String SQL_DELETE = "delete";
    String SQL_DELETE_BY_ID = "deleteById";
    String SQL_INSERT = "insert";
}
