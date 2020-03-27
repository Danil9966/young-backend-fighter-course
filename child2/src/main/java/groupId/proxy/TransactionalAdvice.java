package groupId.proxy;

import groupId.dao.JdbcConnectionHolder;
import groupId.service.IDogService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionalAdvice implements InvocationHandler {

    private Object target;
    private JdbcConnectionHolder connectionHolder;

    public TransactionalAdvice(JdbcConnectionHolder connectionHolder ) {
        this.connectionHolder = connectionHolder;
    }

    public IDogService getTransactionalService(IDogService dogService){
        this.target = dogService;
        ClassLoader cl = this.target.getClass().getClassLoader();

        IDogService iDogService = (IDogService) Proxy.newProxyInstance(cl, new Class[]{IDogService.class}, this);
        return iDogService;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        Connection connection = connectionHolder.createConnection();
        try {
            result = method.invoke(target,args);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return result; }
}
