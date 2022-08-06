package uz.pdp.codingbatrestfullapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDTO {
    @NotNull(message = "Result must be null.")
    private String result;
    @NotNull(message = "Task id must not be null.")
    private Integer taskId;
    @NotNull(message = "The id of the user must not be null.")
    private Integer userId;
}
