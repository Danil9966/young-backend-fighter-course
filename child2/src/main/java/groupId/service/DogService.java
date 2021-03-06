package groupId.service;

import groupId.dao.DogDao;
import groupId.model.Dog;
import groupId.proxy.annotation.Logging;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DogService implements IDogService {

    @Setter
    private DogDao dogDao;

    DogService(DogDao dogDao) {
        this.dogDao = dogDao;
    }

    DogService(){}

    @Override
    public List<Dog> getAllDoggies() {
        return dogDao.getAllDoggies();
    }

    @Override
    @SneakyThrows
    @Transactional
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
