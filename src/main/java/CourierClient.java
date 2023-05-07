import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;


public class CourierClient extends BaseRestClient {
    protected static final String COURIER_URL = BASE_URI + "courier";

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getReqSpec())
                .body(courier)
                .when()
                .post(COURIER_URL)
                .then();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse login(Courier courier) {
        return given()
                .spec(getReqSpec())
                .body(courier)
                .when()
                .post(COURIER_URL + "/login")
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getReqSpec())
                .when()
                .delete(COURIER_URL + "/" + id)
                .then();
    }

}
