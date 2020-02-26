package groupId.dao;

import groupId.model.Dog;

import java.util.List;

public interface DogDao {
    List<Dog> getAllDoggies();

    Dog getDog(Integer id);

    Dog createDog(Dog doggie);

    Dog deleteDog();
}
