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

    public DogService getTransactionalService(Object service) {
        this.target = service;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(service.getClass());

        enhancer.setCallback((org.springframework.cglib.proxy.InvocationHandler) (o, method, args) -> {
            if (method.getReturnType().equals(Dog.class)) {
                Object result = null;
                Connection connection = connectionHolder.createConnection();
                try {
                    result = method.invoke(target, args);
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                } finally {
                    connection.close();
                }
                return result;
            } else return method.invoke(target, args);
        });
        return (DogService) enhancer.create();
    }

    ;
}

