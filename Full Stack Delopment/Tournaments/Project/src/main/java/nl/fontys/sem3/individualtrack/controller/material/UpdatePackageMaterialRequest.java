package nl.fontys.sem3.individualtrack.controller.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePackageMaterialRequest {
    @NotNull
    @Min(1)
    @Max(100000)
    private Long id;

    @NotBlank
    private String material;
}
