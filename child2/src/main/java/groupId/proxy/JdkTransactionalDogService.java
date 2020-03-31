package groupId.proxy;

import groupId.dao.JdbcConnectionHolder;
import groupId.service.IDogService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class JdkTransactionalDogService implements InvocationHandler {

    private Object target;
    private JdbcConnectionHolder connectionHolder;

    public JdkTransactionalDogService(JdbcConnectionHolder connectionHolder ) {
        this.connectionHolder = connectionHolder;
    }

    public static IDogService getTransactionalService(JdbcConnectionHolder connectionHolder, IDogService dogService){
        ClassLoader cl = dogService.getClass().getClassLoader();

        IDogService iDogService = (IDogService) Proxy.newProxyInstance(cl, new Class[]{IDogService.class}, new JdkTransactionalDogService(connectionHolder));
        return iDogService;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        Connection connection = connectionHolder.getConnection();
        try {
            result = method.invoke(target,args);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connectionHolder.closeConnection();
        }
        return result; }
}
