import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;


@RunWith(Parameterized.class)
public class CourierLoginNegativeTest {

    private Courier courier;
    private CourierClient courierClient;


    public CourierLoginNegativeTest(Courier courier) {
        this.courier = courier;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {new Courier("", "xxx")},
                {new Courier("xxx", "")},
                {new Courier("", "")}
        };
    }

    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Аутентификация курьера с невалидными параметрами")
    @Description("POST /api/v1/courier/login → 400 Bad Request || {\"message\": \"Недостаточно данных для входа\"}")
    public void courierLoginNotEnoughDataEmptyTest() {
        courierClient.login(courier).assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));

    }

}

