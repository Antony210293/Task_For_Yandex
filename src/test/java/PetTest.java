import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import pojo.Pet;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;


public class PetTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void getRightPet() {
        given()
                .when()
                .get("/pet/{petId}", 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    public void getWrongPet() {
        given()
                .when()
                .get("/pet/{petId}", 99999999)
                .then()
                .statusCode(404);
    }

    @Test
    public void addNewPet() {
        Pet pet = new Pet (5555, "Reks", "available");
        given()
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Reks"));
    }

    @Test
    public void updatePetName() {
        Pet newPet = new Pet (5555, "Barsik", "available");
        given()
                .header("Content-Type", "application/json")
                .body(newPet)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Barsik"));
    }

    @Test
    public void deletePet() {
        given()
                .when()
                .delete("/pet/{petId}", 5555)
                .then()
                .statusCode(200);
    }

    @Test
    public void findPetsByStatus() {
        given()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("status", everyItem(equalTo("available")));
    }
}
