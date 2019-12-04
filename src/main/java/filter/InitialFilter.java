package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class InitialFilter implements Filter {

    public void init(FilterConfig filterConfig)  {
    // filterConfig.getInitParameter("parameterName");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Log before");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        //default implementation
    }
}