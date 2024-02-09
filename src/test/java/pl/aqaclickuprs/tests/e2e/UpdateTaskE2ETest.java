package pl.aqaclickuprs.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.aqaclickuprs.dto.task.request.CreateTaskRequestDto;
import pl.aqaclickuprs.requests.list.CreateListRequest;
import pl.aqaclickuprs.requests.space.CreateSpaceRequest;
import pl.aqaclickuprs.requests.space.DeleteSpaceRequest;
import pl.aqaclickuprs.requests.task.CreateTaskRequest;
import pl.aqaclickuprs.requests.task.UpdateTaskRequest;

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

        taskId = createTaskStep();
        LOGGER.info("Task ID: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
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

    private String createTaskStep() {

        //We could provide data for this method in to ways, one is using JSON file, the other is using POJO object (in our case named DTO [Data Transfer Object])
        //using DTO (POJO object)
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);
        taskDto.setDescription("Task description E2E Test");
        taskDto.setStatus("to do");
        taskDto.setPriority(null);
        taskDto.setParent(null);
        taskDto.setTime_estimate(null);
        taskDto.setAssignees(null);
        taskDto.setArchived(false);

        // First option - using JSON object
        // final var response = CreateTaskRequest.createTask(json, listId);

        // Second option - using DTO (POJO object)
        final var response = CreateTaskRequest.createTask(taskDto, listId);
        LOGGER.info("CREATED TASK RESPONSE: {}", response);

        Assertions.assertThat(response.getName()).isEqualTo(taskName);
        Assertions.assertThat(response.getDescription()).isEqualTo("Task description E2E Test");

        return response.getId();
    }

    private void updateTaskStep() {
        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "Changed name via PUT method");
        updateTask.put("description", "Changed task description E2E Test");

        final var response = UpdateTaskRequest.updateTask(updateTask, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo("Changed name via PUT method");
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("Changed task description E2E Test");
    }

    private void closeTaskStep() {
        JSONObject closeTask = new JSONObject();
        closeTask.put("status", "complete");

        final var response = UpdateTaskRequest.updateTask(closeTask, taskId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep() {
        final var response = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }
}
