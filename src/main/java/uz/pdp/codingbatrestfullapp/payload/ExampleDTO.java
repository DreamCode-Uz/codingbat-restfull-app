package uz.pdp.codingbatrestfullapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExampleDTO {
    @NotNull
    private String text;
    @NotNull
    private Integer taskId;
}
