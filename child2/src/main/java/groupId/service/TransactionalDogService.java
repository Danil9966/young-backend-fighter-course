package groupId.service;

import groupId.dao.JdbcConnectionHolder;
import groupId.model.Dog;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TransactionalDogService implements IDogService {

    private final IDogService dogService;
    private final JdbcConnectionHolder connectionHolder;
    TransactionalDogService(IDogService dogService, JdbcConnectionHolder connectionHolder){
        this.dogService = dogService;
        this.connectionHolder = connectionHolder;
    }
    @Override
    @SneakyThrows
    public List<Dog> getAllDoggies() {
        List<Dog> result = null;
        Connection connection = connectionHolder.createConnection();
        try {
            result = dogService.getAllDoggies();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    @SneakyThrows
    public Dog getDog(Integer id) {
        Dog result = null;
        Connection connection = connectionHolder.createConnection();
        try {
            result = dogService.getDog(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
        return result;
    }

    @Override
    @SneakyThrows
    public Dog createDog(Dog doggie) {
        Connection connection = connectionHolder.createConnection();
        Dog result = null;

        try {
            result = dogService.createDog(doggie);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @SneakyThrows
    public Dog deleteDog(Integer id) {
        Connection connection = connectionHolder.createConnection();
        Dog result = null;
        try {
            result = dogService.deleteDog(id);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
        return result;
    }

}

