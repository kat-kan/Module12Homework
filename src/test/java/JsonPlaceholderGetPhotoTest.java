import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class JsonPlaceholderGetPhotoTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String PHOTOS = "photos";

    @Test
    public void jsonPlaceholderGetPhotoTest(){

        Response response = given()
                .when()
                .get(BASE_URL + "/" + PHOTOS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        /* check that number of elements in photos is correct according to specification */
        JsonPath json = response.jsonPath();
        List<String> photoTitles = json.getList("title");
        assertEquals(5000, photoTitles.size());

    }

}
