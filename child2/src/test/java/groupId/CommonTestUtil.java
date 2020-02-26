package groupId;

import groupId.model.Dog;

import java.time.LocalDate;

public class CommonTestUtil {
    public static final String DOGGIE_URL = "http://localhost:8080/child2-1.0-SNAPSHOT/";

    public static Dog getConsistentDoggie() {
        return new Dog().setAge(4)
                .setDateOfBirth(LocalDate.of(2020, 1, 21))
                .setName("Сутулая собака")
                .setWeight(3.0)
                .setHeight(1.0);
    }
}
