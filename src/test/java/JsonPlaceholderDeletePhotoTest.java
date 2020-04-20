import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonPlaceholderDeletePhotoTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String PHOTOS = "photos";

    private static Faker faker;
    private static int randomPhotoId;

    @BeforeAll
    public static void  beforeAll(){

        faker = new Faker();
        randomPhotoId = faker.number().numberBetween(1,5000);
    }

    @Test
    public void jsonPlaceholderRemovePhotoDeleteTest(){

        Response response = given()
                .pathParam("photoId", randomPhotoId)
                .when()
                .delete(BASE_URL + "/" + PHOTOS + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
    
}
