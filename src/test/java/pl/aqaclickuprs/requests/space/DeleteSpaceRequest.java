package pl.aqaclickuprs.requests.space;

import io.restassured.response.Response;
import pl.aqaclickuprs.requests.BaseRequest;
import pl.aqaclickuprs.url.ClickupUrl;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {

    public static Response deleteSpace(String spaceId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .delete(ClickupUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
