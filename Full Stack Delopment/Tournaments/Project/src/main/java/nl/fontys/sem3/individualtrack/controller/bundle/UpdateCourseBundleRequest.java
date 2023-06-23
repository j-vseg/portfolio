package nl.fontys.sem3.individualtrack.controller.bundle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseBundleRequest {
    @NotNull
    @Min(1)
    @Max(100000)
    private Long id;

    @NotBlank
    private String name;
    private String description;

    @NotNull
    @Min(0)
    private double price;
    private int quantity;
    private List<BundlePackage> packages;
}
