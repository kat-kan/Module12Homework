import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderDELETEPhotoTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String PHOTOS = "photos";

    private static Faker faker;
    private static int randomPhotoId;

    @BeforeAll
    public static void  beforeAll(){

        faker = new Faker();
        randomPhotoId = faker.number().numberBetween(1,5000);
    }

    @Test
    public void jsonPlaceholderRemovePhotoDELETETest(){

        given()
                .when()
                .delete(BASE_URL + "/" + PHOTOS + "/" + randomPhotoId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
    
}
