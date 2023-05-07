import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

    public class OrderClient extends BaseRestClient {
    protected static final String ORDER_URL = BASE_URI + "orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getReqSpec())
                .body(order)
                .when()
                .post(ORDER_URL)
                .then();
    }

    @Step("Удаление заказа")
    public ValidatableResponse delete(int track) {
        return given()
                .spec(getReqSpec())
                .body("{ \"track\": " + track + "}")
                .when()
                .put(ORDER_URL + "/cancel")
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrdersList() {
        return given()
                .spec(getReqSpec())
                .get(ORDER_URL)
                .then();
    }
}
