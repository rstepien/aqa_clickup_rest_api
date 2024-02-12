package pl.aqaclickuprs.tests.space;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.aqaclickuprs.requests.space.CreateSpaceRequest;
import pl.aqaclickuprs.requests.space.DeleteSpaceRequest;
import pl.aqaclickuprs.requests.space.UpdateSpaceRequest;

class CreateSpaceTest {

    private static final String SPACE_NAME = "Test Space From JAVA";
    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var response = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(SPACE_NAME);


        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "Changed name via PUT method");

        final var spaceId = response.jsonPath().getString("id");

        final var updateResponse = UpdateSpaceRequest.updateSpace(updateTask, spaceId);
        Assertions.assertThat(updateResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(updateResponse.jsonPath().getString("name")).isEqualTo("Changed name via PUT method");

        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }
}
