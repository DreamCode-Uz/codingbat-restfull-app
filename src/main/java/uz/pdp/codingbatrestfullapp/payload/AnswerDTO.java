package uz.pdp.codingbatrestfullapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDTO {
    @NotNull(message = "The result must not be null")
    private String result;
    @NotNull(message = "The taskId must not be null")
    private Integer taskId;
    @NotNull(message = "The userId must not be null")
    private Integer userId;
}
