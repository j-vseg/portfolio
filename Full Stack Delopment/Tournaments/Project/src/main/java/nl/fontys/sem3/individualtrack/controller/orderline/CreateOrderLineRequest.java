package nl.fontys.sem3.individualtrack.controller.orderline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderLineRequest {
    @NotNull
    @Min(1)
    private int quantity;

    @Min(0)
    private double price;

    @NotNull
    private long courseBundle;
}
