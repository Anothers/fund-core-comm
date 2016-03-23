package com.xiangtai.framework.core.interceptor;

import java.sql.Connection;
import java.util.Properties;

import com.xiangtai.framework.core.utils.DBUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

/**
 * @Description: 物理分页拦截器
 * @author: Zhangde
 * @date: 2015-9-28 上午10:28:09
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY);

        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)  
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // 分离最后一个代理对象的目标类  
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }

        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

        String sql = boundSql.getSql();
        StringBuilder sbSql = new StringBuilder();

        // 重写sql  
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        DBUtil dbUtil = new DBUtil(configuration);
        if (dbUtil.isMySQL()) {
            sbSql.append(sql).append(" LIMIT ").append(rowBounds.getOffset()).append(", ").append(rowBounds.getLimit());
            metaStatementHandler.setValue("delegate.boundSql.sql", sbSql.toString());

            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数  
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        } else if (dbUtil.isOracle()) {
            sbSql.append("SELECT * ");
            sbSql.append("  FROM (SELECT ROWNUM RN, NOPAGESQL.*   ");
            sbSql.append("          FROM (").append(sql).append(") NOPAGESQL ");
            sbSql.append("         WHERE ROWNUM <= ").append(rowBounds.getLimit() + rowBounds.getOffset()).append(")");
            sbSql.append(" WHERE RN >= ").append(rowBounds.getOffset());
            metaStatementHandler.setValue("delegate.boundSql.sql", sbSql.toString());

            // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数  
            metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        }

        // 将执行权交给下一个拦截器  
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数  
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub  

    }

}  