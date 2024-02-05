package pl.aqaclickuprs.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.aqaclickuprs.properties.ClickupProperties;
import pl.aqaclickuprs.requests.BaseRequest;
import pl.aqaclickuprs.url.ClickupUrl;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {

    public static Response createSpace(JSONObject space) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(space.toString())
                .when()
                .post(ClickupUrl.getSpacesUrl(ClickupProperties.getTeamId()))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
