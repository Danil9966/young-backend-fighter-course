package groupId.service;

import groupId.model.Dog;
import lombok.SneakyThrows;

import java.util.List;

public interface IDogService {
    @SneakyThrows
    List<Dog> getAllDoggies();

    @SneakyThrows
    Dog getDog(Integer id);

    @SneakyThrows
    Dog createDog(Dog doggie);

    @SneakyThrows
    Dog deleteDog(Integer id);
}
