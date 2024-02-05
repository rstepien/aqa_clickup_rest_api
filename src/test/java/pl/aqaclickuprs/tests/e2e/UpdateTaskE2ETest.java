package pl.aqaclickuprs.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.aqaclickuprs.dto.request.CreateTaskRequestDto;
import pl.aqaclickuprs.requests.list.CreateListRequest;
import pl.aqaclickuprs.requests.space.CreateSpaceRequest;
import pl.aqaclickuprs.requests.task.CreateTaskRequest;

class UpdateTaskE2ETest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static String spaceName = "Space E2E Test";
    private static String listName = "List E2E Test";
    private static String taskName = "Task E2E Test";
    private String spaceId;
    private String listId;
    private String taskId;

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space ID: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List ID: {}", listId);

        taskId = updateTaskStep();
        LOGGER.info("Task ID: {}", taskId);
    }

    private String createSpaceStep() {
        JSONObject json = new JSONObject();
        json.put("name", spaceName);

        final var response = CreateSpaceRequest.createSpace(json);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(spaceName);

        return jsonData.getString("id");
    }

    private String createListStep() {
        JSONObject json = new JSONObject();
        json.put("name", listName);

        final var response = CreateListRequest.createList(json, spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        return jsonData.getString("id");
    }

    private String updateTaskStep() {

        //We could provide data for this method in to ways, one is using JSON file, the other is using POJO object (in our case named DTO [Data Transfer Object])

        //First option - using JSON object
//        JSONObject json = new JSONObject();
//        json.put("name", taskName);
//        json.put("description", "Task description E2E Test");
//        json.put("status", JSONObject.NULL);
//        json.put("priority", JSONObject.NULL);
//        json.put("parent", JSONObject.NULL);
//        json.put("time_estimate", JSONObject.NULL);
//        json.put("assignees", JSONObject.NULL);
//        json.put("archived", false);

        //Second option - using DTO (POJO object)
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);
        taskDto.setDescription("Task description E2E Test");
        taskDto.setStatus(null);
        taskDto.setPriority(null);
        taskDto.setParent(null);
        taskDto.setTime_estimate(null);
        taskDto.setAssignees(null);
        taskDto.setArchived(false);

        // First option - using JSON object
        // final var response = CreateTaskRequest.createTask(json, listId);

        // Second option - using DTO (POJO object)
        final var response = CreateTaskRequest.createTask(taskDto, listId);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(taskName);
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("Task description E2E Test");

        return jsonData.getString("id");
    }
}
