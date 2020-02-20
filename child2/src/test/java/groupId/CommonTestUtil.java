package groupId;

import groupId.model.Dog;

public class CommonTestUtil {

    public static Dog getConsistentDoggie() {
        return new Dog().setAge(4)
//                .setDateOfBirth(LocalDate.of(2020, 1, 21))
                .setName("Сутулая собака")
                .setWeight(3.0);
    }
}
