import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Store;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoreTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void getInventory(){
        given()
                .when()
                .get("store/inventory")
                .then()
                .statusCode(200);
    }

    @Test
    public void addOrderForPet(){
        Store store = new Store(1,12,1,"2025-04-12T15:03:36.612Z", "placed", true);
        given()
                .header("Content-Type", "application/json")
                .body(store)
                .when()
                .post("store/order")
                .then()
                .statusCode(200)
                .body("petId", equalTo(12));
    }

    //Баг - количественные значения запроса не могут принимать отрицательные значения, возвращается код 200, вместо 400
    @Test
    public void addWrongOrderForPet(){
        Store store = new Store(1,-12,-1,"2025-04-12T15:03:36.612Z", "placed", true);
        given()
                .header("Content-Type", "application/json")
                .body(store)
                .when()
                .post("store/order")
                .then()
                .statusCode(400);
    }

    @Test
    public void getOrderById() {
        given()
                .when()
                .get("store/order/{orderId}", 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    //Возможно Баг - возвращается код 404, как не найденный заказ, а не как некорректное значение
    @Test
    public void getOrderByWrongId() {
        given()
                .when()
                .get("store/order/{orderId}", "dfv")
                .then()
                .statusCode(400);
    }

    @Test
    public void getOrderByNonExistId() {
        given()
                .when()
                .get("store/order/{orderId}", 10000)
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteOrderById(){
        given()
                .when()
                .delete("store/order/{orderId}", 1)
                .then()
                .statusCode(200);
    }

    //Возможно Баг - возвращается код 404, как не найденный заказ, а не как некорректное значение
    @Test
    public void deleteOrderByWrongId(){
        given()
                .when()
                .delete("store/order/{orderId}", "dfdfd")
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteOrderByNonExistId(){
        given()
                .when()
                .delete("store/order/{orderId}", 10000)
                .then()
                .statusCode(404);
    }






}
