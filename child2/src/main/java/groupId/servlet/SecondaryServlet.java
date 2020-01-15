package groupId.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SecondaryServlet extends HttpServlet {

    ThreadLocal<List<Integer>> useless;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        // non deamon thread creation
        Thread d = new Thread(() -> {
            while(true){
                int i = 0;
                i++;

            }
        });
        d.setDaemon(false);//наглядности ради
        d.start();

        //setting thread local for memory leak
        List<Integer> uselessArray = new ArrayList<>();
        IntStream.rangeClosed(1, 500).forEach(i -> uselessArray.add(i));
        useless.set(uselessArray);

        //connection refusing attempt
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.write("Hi ");
        out.close();

    }
}