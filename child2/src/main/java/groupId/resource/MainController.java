package groupId.resource;

import groupId.model.Dog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController("/")
public class MainController {

    private static List<Dog> doggies = new ArrayList();

    @PostConstruct
    public void init(){
        doggies.add(new Dog("leyla", LocalDate.now(), 4, 5,1));
    }

    @GetMapping("hi-Mark")
    public String salutMark(){
        return "What a story Mark!";
    }


    @GetMapping("dogs")
    public List<Dog> getAllDoggies(){
        return doggies;
    }

    @PostMapping("dog")
//    @Valid
    public Dog createDoggy(@RequestBody  Dog requestBody){
        doggies.add(requestBody);
        return requestBody;
    }


    @DeleteMapping(value = "/dog/{id}")
    public void deleteDog(@PathVariable Long id) {
        doggies.removeIf(dog -> dog.getId().equals(id));
    }
}
