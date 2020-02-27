package groupId.resource;

import groupId.dao.DogDao;
import groupId.model.Dog;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;


@ResponseBody
@RequestMapping("/")
public class MainController {


    DogDao dogDao;


    public MainController(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    @GetMapping("hi-Mark")
    public String salutMark() {
        return "What a story Mark!";
    }


    @GetMapping("dogs")
    public List<Dog> getAllDoggies() {
        return dogDao.getAllDoggies();
    }

    @GetMapping("dogs/{id}")
    public Dog getDog(@PathVariable Integer id) {
        return dogDao.getDog(id);
    }

    @PostMapping("dog")
    public Dog createDoggy(@RequestBody @Valid Dog requestBody) {
        return dogDao.createDog(requestBody);
    }

    //мб здесь больше подходит @PutMapping
    @DeleteMapping(value = "/dog/{id}")
    public Dog deleteDog(@PathVariable Integer id) {
        return dogDao.deleteDog(id);
    }



}
