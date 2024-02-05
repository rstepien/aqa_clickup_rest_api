package pl.aqaclickuprs.tests;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.aqaclickuprs.requests.space.CreateSpaceRequest;
import pl.aqaclickuprs.requests.space.DeleteSpaceRequest;

import java.util.stream.Stream;

class CreateSpaceWithParamsTest {

    @ParameterizedTest(name = "Create Space with name: {0}")
    @DisplayName("Create Space with valid name")
    @MethodSource("createSpaceData")
    void createSpaceTest(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final var response = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(spaceName);

        final var spaceId = response.jsonPath().getString("id");

        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);

    }

    private static Stream<Arguments> createSpaceData() {
        return Stream.of(
                Arguments.of("TEST SPACE NAME"),
                Arguments.of("123"),
                Arguments.of("abc"),
                Arguments.of("!")
        );
    }
}

