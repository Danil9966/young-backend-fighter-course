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
    public Connection createConnection() {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        datasourceHolder.set(connection);
        return connection;
    }

    public Connection getConnection() {
        return datasourceHolder.get();
    }

    @Override
    protected void finalize() throws Throwable {
        datasourceHolder.remove();
        super.finalize();
    }
}
