package groupId;

import static com.sun.corba.se.impl.util.Version.asString;
import static org.junit.Assert.assertTrue;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Unit test for simple App.
 */
public class AppTest {


    Callable<Integer> ping = () -> {
        try {
            HttpResponse<String> httpResponse = Unirest.get("http://localhost:8080/child2-1.0-SNAPSHOT/home")
                    .asString();
            if (httpResponse.getStatus() == 200) {
                return 200;
            }
            else throw new UnirestException("danno");

        } catch (UnirestException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return -1;

    };

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Integer>> tasks = new ArrayList<>();


        Collection<Callable<Integer>> callables = new ArrayList<>();
        IntStream.rangeClosed(1, 100).forEach(i-> {
            callables.add(ping);
        });

        List<Future<Integer>> futures = executor.invokeAll(callables);
//        futures.forEach(r-> r.g);
        ;

    }
}
