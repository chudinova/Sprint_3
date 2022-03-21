import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient  extends ScooterRestClient {
    private static final String ORDER_PATH = "api/v1/orders/";

    @Step("Создание заказа {0}")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Отмена заказа {0}")
    public ValidatableResponse cancel(int orderTrack) {
        return given()
                .spec(getBaseSpec())
                .body(orderTrack)
                .when()
                .post(ORDER_PATH + "cancel")
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse ordersList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}
