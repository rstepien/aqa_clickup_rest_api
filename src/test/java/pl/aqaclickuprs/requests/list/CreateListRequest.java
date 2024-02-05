package pl.aqaclickuprs.requests.list;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.aqaclickuprs.requests.BaseRequest;
import pl.aqaclickuprs.url.ClickupUrl;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createList(JSONObject list, String spaceId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(list.toString())
                .when()
                .post(ClickupUrl.getListsUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
