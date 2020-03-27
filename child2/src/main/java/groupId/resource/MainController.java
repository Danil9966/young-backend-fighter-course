package groupId.resource;

import groupId.proxy.CglibTransactionalDogService;
import groupId.proxy.TransactionalAdvice;
import groupId.model.Dog;
import groupId.service.DogService;
import groupId.service.IDogService;
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


    private IDogService dogService;
    private final TransactionalAdvice cglibTransactionalDogService;

//    @PostConstruct
    public void init(){
        this.dogService = cglibTransactionalDogService.getTransactionalService(dogService);
    }

    public MainController(IDogService dogService, TransactionalAdvice transactionalService) {
        this.dogService = dogService;
        this.cglibTransactionalDogService = transactionalService;
        init();
    }

    @GetMapping("hi-Mark")
    public String salutMark() {
        return "What a story Mark!";
    }

    @GetMapping("dogs")
    public List<Dog> getAllDoggies() {
        return dogService.getAllDoggies();
    }

    @GetMapping("dogs/{id}")
    public Dog getDog(@PathVariable Integer id) {
        return dogService.getDog(id);
    }

    @PostMapping("dog")
    public Dog createDoggy(@RequestBody @Valid Dog requestBody) {
        return dogService.createDog(requestBody);
    }

    @DeleteMapping(value = "/dog/{id}")
    public Dog deleteDog(@PathVariable Integer id) {
        return dogService.deleteDog(id);
    }



}
