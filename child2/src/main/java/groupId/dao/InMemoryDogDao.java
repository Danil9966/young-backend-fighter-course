package groupId.dao;

import groupId.model.Dog;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InMemoryDogDao implements DogDao {
    private static Map<Integer, Dog> doggies = Collections.synchronizedMap(new HashMap<>());
    private static volatile Integer id = 1;

    @PostConstruct
    public void init() {

        doggies.put(1, new Dog()
                .setName("Leyla")
                .setDateOfBirth(LocalDate.now())
                .setHeight(20.0)
                .setWeight(3.0));
        doggies.put(2, new Dog()
                .setName("Betty")
                .setHeight(30.0)
                .setDateOfBirth(LocalDate.of(2005, 5, 1))
                .setWeight(23.2));
        doggies.put(3, new Dog()
                .setName("Mr Pickles")
                .setHeight(30.0)
                .setDateOfBirth(LocalDate.of(2005, 5, 1))
                .setWeight(23.2));
        doggies.put(4, new Dog()
                .setName("Bim")
                .setHeight(30.0)
                .setDateOfBirth(LocalDate.of(1444, 5, 1))
                .setWeight(23.2));
    }

    @Override
    public List<Dog> getAllDoggies(){
        return doggies.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(dog -> !dog.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Dog getDog(Integer id){
        return doggies.get(id);
    }
    @Override
    public Dog createDog(Dog doggie){
        Dog result;

        do {
            idSequenceCheck();

            result = doggies.putIfAbsent(id, doggie.setId(id));
            id++;
        } while (result != null);

        return doggies.get(doggie.getId());
    }
    @Override
    public Dog deleteDog(Integer id){
        return doggies.computeIfPresent(id, (k, v) -> v.setDeleted(true));
    }

    private void idSequenceCheck() {
        if (id.equals(Integer.MAX_VALUE)) {
            id = 1;
        }
    }

}
