package nl.fontys.sem3.individualtrack.controller.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPackageMaterialsResponse {
    private List<PackageMaterial> packageMaterials;
}
