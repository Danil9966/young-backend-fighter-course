package groupId.proxy;

import groupId.dao.JdbcConnectionHolder;
import groupId.model.Dog;
import groupId.service.DogService;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

public class CglibTransactionalDogService {

    private JdbcConnectionHolder connectionHolder;
    private Object target;

    public CglibTransactionalDogService(JdbcConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }
    public static Object createProxy(JdbcConnectionHolder jdbcConnectionHolder, Object target) {
//        this.target = service;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());

        enhancer.setCallback((org.springframework.cglib.proxy.InvocationHandler) (o, method, args) -> {
            if (method.getReturnType().equals(Dog.class)) {
                Object result = null;
                Connection connection = jdbcConnectionHolder.getConnection();
                try {
                    result = method.invoke(target, args);
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                } finally {
                    jdbcConnectionHolder.closeConnection();
                }
                return result;
            } else return method.invoke(target, args);
        });
        return enhancer.create();
    }

    ;
}

