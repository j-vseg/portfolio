package nl.fontys.sem3.individualtrack.controller.bpackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBundlePackageRequest {
    @NotBlank
    private String name;
    private String description;

    @NotNull
    @Min(0)
    private double price;
    private List<PackageMaterial> materials;
}
