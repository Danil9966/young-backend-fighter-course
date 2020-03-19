package groupId.service;

import groupId.dao.DogDao;
import groupId.model.Dog;
import lombok.SneakyThrows;

import java.util.List;

public class DogService implements IDogService {

    private final DogDao dogDao;

    DogService(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    @Override
    public List<Dog> getAllDoggies() {
        return dogDao.getAllDoggies();
    }

    @Override
    @SneakyThrows
    public Dog getDog(Integer id) {
        return dogDao.getDog(id);
    }

    @Override
    @SneakyThrows
    public Dog createDog(Dog doggie) {
        return dogDao.createDog(doggie);
    }

    @Override
    @SneakyThrows
    public Dog deleteDog(Integer id) {
        return dogDao.deleteDog(id);
    }

}
