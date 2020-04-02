package groupId.dao;

import groupId.model.Dog;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicTreeUI;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class JdbcDogDao implements DogDao {

    private final JdbcTemplate jdbcTemplate;


    JdbcDogDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Dog> getAllDoggies() {
        List<Dog> resultList;
        resultList = jdbcTemplate
                .query("SELECT * FROM DOGGIES WHERE deleted = FALSE",
                        (Object[]) null, new BeanPropertyRowMapper<>(Dog.class));

        return resultList;
    }

    @Override
    public Dog getDog(Integer id) {
        List<Dog> resultList;
        resultList = jdbcTemplate
                .query("SELECT * FROM DOGGIES WHERE id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Dog.class));

        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Dog createDog(Dog doggie) {
        KeyHolder kh = new GeneratedKeyHolder();
        ;
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO DOGGIES (name, height, weight, age, dateOfBirth)" +
                            " VALUES " +
                            " (? ,?, ?, ?, ?);",
                    new String[]{"id"});
            ps.setString(1, doggie.getName());
            ps.setDouble(2, doggie.getHeight());
            ps.setDouble(3, doggie.getWeight());
            ps.setDouble(4, doggie.getAge());

            ps.setDate(5, Date.valueOf(doggie.getDateOfBirth()));
            return ps;
        }, kh);
        return getDog((Integer) kh.getKey());

    }

    @Override
    public Dog deleteDog(Integer id) {
        String delete = "UPDATE DOGGIES SET deleted = TRUE WHERE id = ?;";
        int count = jdbcTemplate.update(delete, id);

        if (count > 0) {
            return getDog(id);
        } else {
            return null;
        }
    }
}

