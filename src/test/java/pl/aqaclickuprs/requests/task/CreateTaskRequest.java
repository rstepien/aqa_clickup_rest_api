package pl.aqaclickuprs.requests.task;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.aqaclickuprs.dto.task.request.CreateTaskRequestDto;
import pl.aqaclickuprs.dto.task.response.CreateTaskResponseDto;
import pl.aqaclickuprs.requests.BaseRequest;
import pl.aqaclickuprs.url.ClickupUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTask(JSONObject task, String listId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .post(ClickupUrl.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

    public static CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto, String listId) {

        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto)  //if we want to use DTO we need to use this line and remove .toString() from json.toString() in the previous method.
                .when()
                .post(ClickupUrl.getTasksUrl(listId))
                .then()
                .statusCode(200)
                .log().ifError()
                .extract()
                .response()
                .as(CreateTaskResponseDto.class);
    }
}
