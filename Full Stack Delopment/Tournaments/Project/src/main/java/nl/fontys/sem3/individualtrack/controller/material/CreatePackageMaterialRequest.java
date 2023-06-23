package nl.fontys.sem3.individualtrack.controller.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePackageMaterialRequest {
    @NotBlank
    private String material;
}
