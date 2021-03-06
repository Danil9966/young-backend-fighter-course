package groupId.proxy;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {

    @Pointcut("@annotation(groupId.proxy.annotation.Logging)")
    public void callOnLoggingAnnotation() {
    }

    @After("callOnLoggingAnnotation()")
    public void beforeCallAtLogAdvice(JoinPoint jp) {
        Logger logger = Logger.getLogger(jp.getTarget().getClass().getName());
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        logger.info("Before " + jp.toString() + ", args=[" + args + "]");
    }

}
