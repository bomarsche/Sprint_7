import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRestClient {

    protected static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/api/v1/";

    protected RequestSpecification getReqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
}
