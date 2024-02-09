package pl.aqaclickuprs.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskCreatorResponseDto {
    @JsonProperty("user_name") // to avoid conflicts with textContent field in Task class (camel case)

    private String userName;
    private String email;

}
