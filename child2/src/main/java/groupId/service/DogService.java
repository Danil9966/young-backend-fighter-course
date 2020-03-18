package groupId.service;

import groupId.dao.DogDao;
import groupId.dao.JdbcConnectionHolder;
import groupId.model.Dog;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DogService {

    private final DogDao dogDao;
    private final JdbcConnectionHolder connectionHolder;

    DogService(DogDao dogDao, JdbcConnectionHolder connectionHolder) {
        this.dogDao = dogDao;
        this.connectionHolder = connectionHolder;
    }

    @SneakyThrows
    public List<Dog> getAllDoggies() {
        List<Dog> result = null;
        Connection connection = connectionHolder.createConnection();
        try {
            result = dogDao.getAllDoggies();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return result;
    }

    @SneakyThrows
    public Dog getDog(Integer id) {
        Dog result = null;
        Connection connection = connectionHolder.createConnection();
        try {
            result = dogDao.getDog(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return result;
    }

    @SneakyThrows
    public Dog createDog(Dog doggie) {
        Connection connection = connectionHolder.createConnection();
        Dog result = null;

        try {
            result = dogDao.createDog(doggie);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @SneakyThrows
    public Dog deleteDog(Integer id) {
        Connection connection = connectionHolder.createConnection();
        Dog result = null;
        try {
            result = dogDao.deleteDog(id);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
        return result;
    }

}
