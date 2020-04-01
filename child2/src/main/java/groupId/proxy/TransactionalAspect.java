package groupId.proxy;

import groupId.dao.JdbcConnectionHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class TransactionalAspect {


    private final JdbcConnectionHolder connectionHolder;

    public TransactionalAspect(JdbcConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Pointcut("execution(* groupId.service.IDogService.*(..))")
    public void addTransactionToDogService() {
    }

    @Around("addTransactionToDogService()")
    public Object addTransactionToDogServiceAdvice(ProceedingJoinPoint pjp) {
        Object result = null;
        connectionHolder.getConnection();
        System.out.println("ASPECT JJJ");
        try {
            result = pjp.proceed();
            connectionHolder.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            connectionHolder.rollback();
        } finally {
            connectionHolder.closeConnection();
        }
        return result;
    }
}
