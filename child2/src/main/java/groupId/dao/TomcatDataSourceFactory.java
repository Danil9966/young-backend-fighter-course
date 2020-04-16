package groupId.dao;

import org.springframework.beans.factory.FactoryBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TomcatDataSourceFactory implements FactoryBean<DataSource> {
    @Override
    public DataSource getObject() throws Exception {
        InitialContext ctx = new InitialContext();
        Context initCtx  = (Context) ctx.lookup("java:/comp/env");
        DataSource ds = (DataSource) initCtx.lookup("jdbc/h2db");
        return ds;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
