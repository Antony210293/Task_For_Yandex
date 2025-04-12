import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void createUser(){
        User user = new User(1, "bryan", "Anton", "Bondarev", "bryan_24@mail.ru", "ABC1234", "79110950788", 1);
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("user")
                .then()
                .statusCode(200);
    }

    //Баг - Возвращается статус 200 при пустом поле Username
    @Test
    public void createUserWithWrongUsername(){
        User user = new User(1, "", "Anton", "Bondarev", "bryan_24@mail.ru", "ABC1234", "79110950788", 1);
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("user")
                .then()
                .statusCode(200);
    }

    @Test
    public void getUsername(){
        given()
                .when()
                .get("user/{username}", "bryan")
                .then()
                .statusCode(200)
                .body("username", equalTo("bryan"));
    }

    //Вместо 400 возвращается 404
    @Test
    public void getWrongUsername() {
        given()
                .when()
                .get("/user/{username}", " ")
                .then()
                .statusCode(400);
    }

    @Test
    public void getNonExistingUser() {
        given()
                .when()
                .get("/user/{username}", "effefrfrfr")
                .then()
                .statusCode(404);
    }

    @Test
    public void updateUser(){
        User user = new User(1, "bryan", "Anton", "Bondarev", "bryan_24@mail.ru", "ABC1234", "79110950788", 1);
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .put("user/{username}", "bryan24")
                .then()
                .statusCode(200);
    }

    //Возвращается код 200 вместо 404
    @Test
    public void updateNonExistingUser() {
        User user = new User(1, "bryan45", "Anton", "Bondarev", "bryan_24@mail.ru", "ABC1234", "79110950788", 1);
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .put("user/{username}", "bryan24")
                .then()
                .statusCode(404);
    }

    //Возвращается код 200 вместо 400
    @Test
    public void updateWithWrongUser() {
        User user = new User(1, "bryan", "Anton", "Bondarev", "bryan_24@mail.ru", "ABC1234", "79110950788", 1);
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .put("user/{username}", " ")
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteUser() {
        given()
                .when()
                .delete("user/{username}", "bryan24")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteNonExistingUser() {
        given()
                .when()
                .delete("/user/{username}", "rfrfrfrfr")
                .then()
                .statusCode(404);
    }

    //Вместо 400 возвращает 404
    @Test
    public void deleteUserWithInvalidUsername() {
        given()
                .when()
                .delete("/user/{username}", " ")
                .then()
                .statusCode(400);
    }
}
