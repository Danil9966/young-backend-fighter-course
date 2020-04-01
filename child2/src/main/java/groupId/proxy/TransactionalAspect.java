package groupId.proxy;

import groupId.dao.JdbcConnectionHolder;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;


@Aspect
public class TransactionalAspect {


    private final DataSource dataSource;

    public TransactionalAspect(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Pointcut("execution(* groupId.service.IDogService.*(..))")
    public void addTransactionToDogService() {
    }

    @Around("addTransactionToDogService()")
    @SneakyThrows
    public Object addTransactionToDogServiceAdvice(ProceedingJoinPoint pjp) {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        connection.setAutoCommit(false);

        Object result = null;
        System.out.println("ASPECT JJJ");
        try {
            result = pjp.proceed();
            connection.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            DataSourceUtils.releaseConnection(connection,dataSource);
        }
        return result;
    }
}
