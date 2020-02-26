package groupId.dao;

import groupId.model.Dog;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
                        "  dateOfBirth double NOT NULL,\n" +
                        "  deleted bit default false ,\n" +

                        ");");

        statement.execute(
                "INSERT INTO DOGGIE (name, height, weight, dateOfBirth" +
                        " VALUES ('Leyla',20.0, 3.0, '2019.05.05'))" +
                        " ('Betty',20.0, 3.0, '2019.05.05'))" +
                        " ('Mr Pickles',20.0, 3.0, '2019.05.05'))" +
                        " ('Bim',20.0, 3.0, '2019.05.05'))" +
                        " ('Leyla',20.0, 3.0, '2019.05.05'))" +


        )

        ; ");
    }


    //      doggies.put(1, new Dog()
//                .setName("Leyla")
//                .setDateOfBirth(LocalDate.now())
//            .setHeight(20.0)
//                .setWeight(3.0));
//        doggies.put(2, new Dog()
//                .setName("Betty")
//                .setHeight(30.0)
//                .setDateOfBirth(LocalDate.of(2005, 5, 1))
//            .setWeight(23.2));
//        doggies.put(3, new Dog()
//                .setName("Mr Pickles")
//                .setHeight(30.0)
//                .setDateOfBirth(LocalDate.of(2005, 5, 1))
//            .setWeight(23.2));
//        doggies.put(4, new Dog()
//                .setName("Bim")
//                .setHeight(30.0)
//                .setDateOfBirth(LocalDate.of(1444, 5, 1))
//            .setWeight(23.2));
    @Override
    public List<Dog> getAllDoggies() {
        return null;
    }

    @Override
    public Dog getDog(Integer id) {
        return null;
    }

    @Override
    public Dog createDog(Dog doggie) {
        return null;
    }

    @Override
    public Dog deleteDog() {
        return null;
    }
}

