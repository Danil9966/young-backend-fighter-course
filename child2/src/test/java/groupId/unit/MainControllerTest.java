package groupId.unit;

import groupId.model.Dog;
import groupId.resource.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

public class MainControllerTest {
    @Autowired
    MainController controller;

    Validator validator;
    @BeforeClass
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testNegativeAge(){
        Dog doggie = new Dog().setAge(4).setName("fsdaf").setDateOfBirth(LocalDate.of(2020, 1, 21)).setWeight(3.0).setId(9);
        controller.createDoggy(doggie);
        System.out.println(validator.validate(doggie).size());
    }
}
