package pl.aqaclickuprs.requests.space;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.aqaclickuprs.requests.BaseRequest;
import pl.aqaclickuprs.url.ClickupUrl;

import static io.restassured.RestAssured.given;

public class UpdateSpaceRequest {
    public static Response updateSpace(JSONObject updateTask, String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(updateTask.toString())
                .when()
                .put(ClickupUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}