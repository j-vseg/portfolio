package nl.fontys.sem3.individualtrack.business.material;

import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

import java.util.List;

public interface GetAllPackageMaterialsUseCase {
    List<PackageMaterial> getMaterials(long packageId);
}
