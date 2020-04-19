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

public class JsonPlaceholderPUTPATCHPhotoTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String PHOTOS = "photos";

    private static Faker faker;
    private static String fakeTitleFromHP;
    private static int randomPhotoId;

    private int randomAlbumId;
    private String fakeUrl;
    private String fakeThumbnailUrl;

    @BeforeAll
    public static void  beforeAll(){

        faker = new Faker();
        fakeTitleFromHP = faker.harryPotter().quote();
        randomPhotoId = faker.number().numberBetween(1,5000);
    }

    @BeforeEach
    public void beforeEach(){

        randomAlbumId = faker.number().numberBetween(1,100);
        fakeUrl = faker.internet().url();
        fakeThumbnailUrl = faker.internet().url();
    }

    @Test
    public void jsonPlaceholderUpdatePhotoPUTTest(){

        JSONObject photo = new JSONObject();
        photo.put("albumId", randomAlbumId);
        photo.put("title", fakeTitleFromHP);
        photo.put("url", fakeUrl);
        photo.put("thumbnailUrl", fakeThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .pathParam("photoId", randomPhotoId)
                .when()
                .put(BASE_URL + "/" + PHOTOS + "/{photoId}" )
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(randomAlbumId, json.getInt("albumId"));
        assertEquals(fakeTitleFromHP, json.get("title"));
        assertEquals(fakeUrl, json.get("url"));
        assertEquals(fakeThumbnailUrl, json.get("thumbnailUrl"));
    }

    @Test
    public void jsonPlaceholderUpdatePhotoPATCHTest(){

        JSONObject photo = new JSONObject();
        photo.put("title", fakeTitleFromHP);

        Response response = given()
                .contentType("application/json")
                .body(photo.toString())
                .pathParam("photoId", randomPhotoId)
                .when()
                .patch(BASE_URL + "/" + PHOTOS + "/{photoId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(fakeTitleFromHP, json.get("title"));
    }
}
