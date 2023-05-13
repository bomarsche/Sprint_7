
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;


@RunWith(Parameterized.class)
public class CourierCreationNegativeTest {

    private Courier courier;
    private CourierClient courierClient;

    public CourierCreationNegativeTest(Courier courier) {
        this.courier = courier;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {CourierGenerator.getRandomWithoutLogin()},
                {CourierGenerator.getRandomWithoutPassword()},
                {CourierGenerator.getRandomEmpty()},
        };
    }

    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }


    @Test
    @DisplayName("Создание курьера без одного из параметров")
    @Description("POST /api/v1/courier → 400 Bad Request || {\"message\": \"Недостаточно данных для создания учетной записи\"}")
    public void courierCreationNotEnoughDataEmptyTest() {
        courierClient.create(courier).assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }
}
