package nl.fontys.sem3.individualtrack.business.material;

import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

public interface UpdatePackageMaterialUseCase {
    void updateMaterial(long packageId, PackageMaterial material);
}
