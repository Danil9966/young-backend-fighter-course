package groupId.restAssured;

import static groupId.CommonTestUtil.getConsistentDoggie;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


import groupId.model.Dog;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TrivialTest {

    private static final String URL = "http://localhost:8080/child2-1.0-SNAPSHOT/";

    @Test
    public void healthCheck() {

        given().when().get(URL + "dogs/1")
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
                .request("POST", URL + "dog")
                .then()
                .statusCode(400);
    }

    @Test
    public void creatingOfDogWithNegativeWeightFails() {
        with().body(getConsistentDoggie().setWeight(-10.))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", URL + "dog")
                .then()
                .statusCode(400);
    }

    @Test
    public void creatingOfDogWithBlankNameFails() {
        with().body(getConsistentDoggie().setName(""))
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", URL + "dog")
                .then()
                .statusCode(400);
    }


    @Test
    public void creatingOfNormalDogSuccess() {
        with().body(getConsistentDoggie())
                .with()
                .contentType(ContentType.JSON)
                .when()
                .request("POST", URL + "dog")
                .then()
                .statusCode(200);
    }

    @Test
    public void successfullDeletingOfDog() {
                with()
                .contentType(ContentType.JSON)
                .when()
                .request("DELETE", URL + "dog/3")
                .then()
                .statusCode(200).body("name", equalTo("Mr Pickles"));



                //как по нормальному работать с массивами в rest assured
//        given().when().get(URL + "dogs")
//                .then()
//                .assertThat()
//                .body("", hasItems(""));

    }


}

