package groupId.dao;

import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcConnectionHolder {
    private final ThreadLocal<Connection> datasourceHolder =
            new ThreadLocal<>();

    private final DataSource dataSource;

    @SneakyThrows
    JdbcConnectionHolder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    private Connection createConnection() {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        datasourceHolder.set(connection);
        return connection;
    }

    public Connection getConnection() {
        if (datasourceHolder.get() == null) {
            this.createConnection();
        }
        return datasourceHolder.get();
    }

    @SneakyThrows
    public void commit() {
        if (datasourceHolder.get() != null) {
            datasourceHolder.get().commit();
        }
    }


    @SneakyThrows
    public void rollback() {
        if (datasourceHolder.get() != null) {
            datasourceHolder.get().rollback();
        }
    }

    @SneakyThrows
    public void closeConnection() {
        if (datasourceHolder.get() != null) {
            datasourceHolder.get().close();
            datasourceHolder.remove();
        }
    }

}
