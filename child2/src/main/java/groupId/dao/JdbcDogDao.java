package groupId.dao;

import groupId.model.Dog;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class JdbcDogDao implements DogDao {

    private Flyway flyway;

    private DataSource dataSource;

    JdbcDogDao(DataSource dataSource, Flyway flyway) throws SQLException {
        this.flyway = flyway;
        this.dataSource = dataSource;
//        this.flyway.migrate();


    }

    @Override
    public List<Dog> getAllDoggies() {
        List<Dog> resultList = new ArrayList<>();
        Connection connection;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DOGGIES WHERE deleted = false");

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
            connection.close();
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
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DOGGIES WHERE id = " + id);

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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Dog createDog(Dog doggie) {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format("INSERT INTO DOGGIES (name, height, weight, age, dateOfBirth)" +
                                    " VALUES " +
                                    " ('%s',%s, %s, %s, '%s');",
                            doggie.getName(), doggie.getHeight(), doggie.getWeight(), doggie.getAge(), doggie.getDateOfBirth())
            );

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return doggie;
    }

    @Override
    public Dog deleteDog(Integer id) {

        Dog result = null;
        Connection connection;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format("UPDATE DOGGIES SET deleted = true where id = %s;",
                            id)
            );
            //?
            result = getDog(id);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}

