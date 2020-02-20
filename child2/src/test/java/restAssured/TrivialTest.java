package restAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;


import groupId.model.Dog;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TrivialTest {

    private static final String URL = "http://localhost:8080/child2-1.0-SNAPSHOT/";

    @Test
    public void makeSureThatGoogleIsUp() {

        given().when().get(URL + "dogs/1")
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("Leyla"));
    }

    @Test
    public void creatingOfInconsistentDogFails() {
        with().body(new Dog().setAge(4)
//                .setDateOfBirth(LocalDate.of(2020, 1, 21))
                .setName("fsdaf").setWeight(3.0).setId(9))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", URL + "dog")
                .then()
                .assertThat().body("", equalTo("sdf"));
//                .statusCode(201);
    }
}

