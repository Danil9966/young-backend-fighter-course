package groupId.resource;

import groupId.model.Dog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class MainController {
    private static List<Dog> doggies = new ArrayList();


    @GetMapping("dogs")
    public List<Dog> getAllDoggies(){
        return doggies;
    }


    @PostMapping("dog")
    public Dog createDoggy(@RequestBody  Dog requestBody){
        doggies.add(requestBody);
        return requestBody;
    }
}
