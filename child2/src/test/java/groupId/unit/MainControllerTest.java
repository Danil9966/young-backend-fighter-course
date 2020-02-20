package groupId.unit;

import groupId.model.Dog;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

import static groupId.CommonTestUtil.getConsistentDoggie;
import static org.testng.Assert.assertTrue;

public class MainControllerTest {

    Validator validator;

    @BeforeClass
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testNegativeAge(){

        Dog doggie = getConsistentDoggie()
                .setDateOfBirth(LocalDate.of(2028, 1, 21));
        assertTrue(isValidationFailed(doggie));
    }
    @Test
    public void testBlankNameDog(){

        Dog doggie = getConsistentDoggie()
                .setName("");
        assertTrue(isValidationFailed(doggie));
    }

    @Test
    public void testConsistentDogCreation(){

        Dog doggie = getConsistentDoggie();

        assertTrue(!isValidationFailed(doggie));
    }

    private boolean isValidationFailed(Dog doggie) {
        return !validator.validate(doggie).isEmpty();
    }
}
