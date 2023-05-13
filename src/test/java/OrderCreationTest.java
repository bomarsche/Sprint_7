import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderCreationTest {

    private Order order;
    private OrderClient orderClient;
    private ValidatableResponse createOrder;

    public OrderCreationTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {OrderGenerator.getRandomFullData()},
                {OrderGenerator.getRandomFullDataWithSeveralColours()},
                {OrderGenerator.getRandomDataWithoutColour()},
        };
    }


    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Создание заказа со всеми необходимыми параметрами")
    @Description("POST /api/v1/courier → 201 CREATED || {\"track\": 11111}")
    public  void orderCreationPositiveTest() {
        createOrder = orderClient.create(order).assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());
    }

    @After
    public void deleteOrder() {
        int track = createOrder.extract().path("track");
        orderClient.delete(track);
    }

}

