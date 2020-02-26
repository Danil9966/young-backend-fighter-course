package groupId.dao;

import groupId.model.Dog;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class JdbcDogDao implements DogDao {


    DataSource dataSource;

    JdbcDogDao(DataSource dataSource) throws SQLException {
        final Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE DOGGIE (\n" +
                        "  id INTEGER NOT NULL auto_increment primary key ,\n" +
                        "  name varchar(255) NOT NULL default '',\n" +
                        "  height double NOT NULL,\n" +
                        "  weight double NOT NULL,\n" +
                        "  age INTEGER ,\n" +
                        "  dateOfBirth double NOT NULL,\n" +
                        "  deleted bit default false ,\n" +

                        ");");

        statement.executeUpdate(
                "INSERT INTO DOGGIE (name, height, weight, dateOfBirth)" +
                        " VALUES " +
                        " ('Leyla',20.0, 3.0, '2019.05.05')" +
                        " ('Betty',20.0, 3.0, '2005.05.01')" +
                        " ('Mr Pickles',20.0, 3.0, '2005.05.01')" +
                        " ('Bim',20.0, 3.0, '1444.05.01');"
        );

        connection.close();

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
                    String.format("INSERT INTO DOGGIE (name, height, weight, age, dateOfBirth)" +
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
    public Dog deleteDog(Integer id){

        Dog result = null;
        Connection connection;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    String.format("UPDATE DOG SET deleted = true where id = %s);",
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

