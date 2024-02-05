package pl.aqaclickuprs.tests;

import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.aqaclickuprs.properties.ClickupProperties;
import pl.aqaclickuprs.url.ClickupUrl;

import static io.restassured.RestAssured.given;

class CreateSpaceTest {

    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", "Test Space From RestAssured");

        final var response = given()
                .header("Authorization", ClickupProperties.getToken())
                .contentType(ContentType.JSON)
                .body(space.toString())
                .when()
                .post(ClickupUrl.getBaseUrl() + "/team/" + ClickupProperties.getTeamId() + "/space")
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo("Test Space From RestAssured");

    }
}
