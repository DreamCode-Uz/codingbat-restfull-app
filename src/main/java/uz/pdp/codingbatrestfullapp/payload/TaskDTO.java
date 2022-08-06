package uz.pdp.codingbatrestfullapp.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TaskDTO {

    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Task description must be null")
    @Size(min = 30, max = 255)
    private String description;

    private String solution;

    private String hint;

    @NotNull(message = "method must not be null.")
    private String method;

    @NotNull(message = "The hasStar value must be entered.")
    private boolean hasStar;

    @NotNull(message = "categoryId must not be null.")
    private Integer categoryId;
}
