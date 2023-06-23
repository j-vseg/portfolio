package nl.fontys.sem3.individualtrack.business.material;

import nl.fontys.sem3.individualtrack.domain.PackageMaterial;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

public interface GetPackageMaterialUseCase {
    Optional<PackageMaterial> getMaterial(long id);
}
