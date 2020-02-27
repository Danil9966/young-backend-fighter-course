package groupId.restAssured;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static groupId.CommonTestUtil.DOGGIE_URL;
import static groupId.CommonTestUtil.getConsistentDoggie;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;

public class TrivialTest {


    @Test
    public void healthCheck() {

        given().when().get(DOGGIE_URL + "dogs/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("Leyla"));
    }

    @Test
    public void creatingOfDogWithNullWeightFails() {
        with().body(getConsistentDoggie().setWeight(null))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", DOGGIE_URL + "dog")
                .then()
                .statusCode(400);
    }

    @Test
    public void creatingOfDogWithNegativeWeightFails() {
        with().body(getConsistentDoggie().setWeight(-10.))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", DOGGIE_URL + "dog")
                .then()
                .statusCode(400);
    }

    @Test
    public void creatingOfDogWithBlankNameFails() {
        with().body(getConsistentDoggie().setName(""))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", DOGGIE_URL + "dog")
                .then()
                .statusCode(400);
    }


    @Test
    public void creatingOfNormalDogSuccess() {
        with().body(getConsistentDoggie())
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", DOGGIE_URL + "dog")
                .then()
                .statusCode(200)
                .body("name", equalTo(getConsistentDoggie().getName()));
    }

    @Test
    public void successfullDeletingOfDog() {
        checkNumberOfAvailableDogsEqualTo(4);

        with()
                .contentType(ContentType.JSON)
                .when()
                .request("DELETE", DOGGIE_URL + "dog/3")
                .then()
                .statusCode(200).body("name", equalTo("Mr Pickles"));


        checkNumberOfAvailableDogsEqualTo(3);

    }

    private void checkNumberOfAvailableDogsEqualTo(int numOfDogs) {
        assert (given().when().get(DOGGIE_URL + "dogs")
                .thenReturn()
                .as(ArrayList.class, ObjectMapperType.JACKSON_2).size()== numOfDogs);
    }


}

