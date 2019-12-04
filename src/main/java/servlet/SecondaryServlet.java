package servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecondaryServlet  extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp ) throws IOException
    {
        ServletContext ctx = req.getServletContext();
//        ctx.setAttribute( "user", ",p;," );
        String user = (String) ctx.getAttribute( "user" );
        ctx.removeAttribute( "user" );

        PrintWriter out = resp.getWriter();
//        out.write( "Hi " + user );
    }
}