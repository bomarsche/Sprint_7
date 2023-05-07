import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

public class CourierCreationPositiveTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;


    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomFullData();
    }

    @Test
    @DisplayName("Создание курьера с валидными данными")
    @Description("POST /api/v1/courier → 201 Created || {ok: true}")
    public void courierCreationPositiveTest() {
        ValidatableResponse createResponse = courierClient.create(courier);
        createResponse.assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера с уже зарегистрированным логином")
    @Description("POST /api/v1/courier → 409 Conflict || {\"message\": \"Этот логин уже используется. Попробуйте другой.\"}")
    public void courierCreationRecurringLoginTest() {
        ValidatableResponse createResponse = courierClient.create(courier);
        createResponse.assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));

        Courier recurringCourier = new Courier(courier.getLogin(), "1234567", "recurring");
        ValidatableResponse createRecurringResponse = courierClient.create(recurringCourier);
        createRecurringResponse.assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void cleanUp(){
        courierId = courierClient.login(courier)
                .extract().path("id");
        courierClient.delete(courierId);

    }
}
