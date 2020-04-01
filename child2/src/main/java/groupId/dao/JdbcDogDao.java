package groupId.dao;

import groupId.model.Dog;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class JdbcDogDao implements DogDao {

    private DataSource dataSource;

    JdbcDogDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Dog> getAllDoggies() {
        List<Dog> resultList = new ArrayList<>();
        Connection connection;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DOGGIES WHERE deleted = false");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int i = 1;
                resultList.add(new Dog()
                        .setId(resultSet.getInt(i++))
                        .setName(resultSet.getString(i++))
                        .setHeight(resultSet.getDouble(i++))
                        .setWeight(resultSet.getDouble(i++))
                        .setAge(resultSet.getInt(i++))
                        .setDateOfBirth(LocalDate.from(resultSet.getTimestamp(i).toLocalDateTime())

                        ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Dog getDog(Integer id) {
        Dog result = new Dog();
        Connection connection;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM DOGGIES WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int i = 1;
                result
                        .setId(resultSet.getInt(i++))
                        .setName(resultSet.getString(i++))
                        .setHeight(resultSet.getDouble(i++))
                        .setWeight(resultSet.getDouble(i++))
                        .setAge(resultSet.getInt(i++))
                        .setDateOfBirth(LocalDate.from(resultSet.getTimestamp(i).toLocalDateTime()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Dog createDog(Dog doggie) {
        Connection connection;
        Integer id;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO DOGGIES (name, height, weight, age, dateOfBirth)" +
                    " VALUES " +
                    " (? ,?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, doggie.getName());
            statement.setDouble(2, doggie.getHeight());
            statement.setDouble(3, doggie.getWeight());
            statement.setInt(4, doggie.getAge());
            statement.setDate(5, Date.valueOf(doggie.getDateOfBirth()));
            id = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return getDog(id);
    }

    @Override
    public Dog deleteDog(Integer id) {

        Dog result = null;
        Connection connection;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement("UPDATE DOGGIES SET deleted = true where id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();

            result = getDog(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}

