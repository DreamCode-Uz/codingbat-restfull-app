package uz.pdp.codingbatrestfullapp.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CategoryDTO {

    @NotNull(message = "Category name should not be null.")
    private String name;

    @NotNull(message = "languageId must not be null.")
    @NotEmpty(message = "languageId must not be empty.")
    private Set<Integer> languagesId;
}
