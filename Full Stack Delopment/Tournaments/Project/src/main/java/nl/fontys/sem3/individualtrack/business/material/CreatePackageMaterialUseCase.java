package nl.fontys.sem3.individualtrack.business.material;

import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

public interface CreatePackageMaterialUseCase {
    PackageMaterial createMaterial(long packageId, PackageMaterial packageMaterial);
}
