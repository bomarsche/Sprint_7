import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;


public class CourierLoginPositiveTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;


    @Before
    public void setUpCourier(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomFullData();
        courierClient.create(courier);
    }

    @Test
    @DisplayName("Проверка успешной аутентификации курьера")
    @Description("POST /api/v1/courier/login → 200 OK || {id: %user_id%}")
    public void courierLoginPositiveTest(){
        courierClient.login(courier).assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Проверка аутентификации курьера c некорректным паролем")
    @Description("POST /api/v1/courier/login → 404 NOT FOUND ||{\"message\": \"Учетная запись не найдена\"\"}")
    public void courierLoginIncorrectPass() {
        courierClient.login(new Courier(courier.getLogin(), "xxx"))
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Проверка аутентификации курьера c некорректным логином")
    @Description("POST /api/v1/courier/login → 404 NOT FOUND ||{\"message\": \"Учетная запись не найдена\"\"}")
    public void courierLoginIncorrectLogin() {
        courierClient.login(new Courier("xxx", courier.getPassword()))
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body( "message", equalTo("Учетная запись не найдена"));
    }


    @After
    public void cleanUp(){
        courierId = courierClient.login(courier)
                .extract().path("id");
        courierClient.delete(courierId);
    }
}
