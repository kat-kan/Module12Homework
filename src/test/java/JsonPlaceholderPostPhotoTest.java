import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholderPostPhotoTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String PHOTOS = "photos";

    private static Faker faker;
    private int randomAlbumId;
    private String fakeTitleFromHitchiker;
    private String fakeUrl;
    private String fakeThumbnailUrl;

    @BeforeAll
    public static void  beforeAll(){

        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach(){

        randomAlbumId = faker.number().numberBetween(1,100);
        fakeTitleFromHitchiker = faker.hitchhikersGuideToTheGalaxy().marvinQuote();
        fakeUrl = faker.internet().url();
        fakeThumbnailUrl = faker.internet().url();
    }

    @Test
    public void jsonPlaceholderCreateNewPhotoTest(){

        JSONObject photo = new JSONObject();
        photo.put("albumId", randomAlbumId);
        photo.put("title", fakeTitleFromHitchiker);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .when()
                .post(BASE_URL + "/" + PHOTOS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(randomAlbumId, json.getInt("albumId"));
        assertEquals(fakeTitleFromHitchiker, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
    }
}
