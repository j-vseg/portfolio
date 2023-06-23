package nl.fontys.sem3.individualtrack.business.impl.material;

import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.PackageMaterialEntity;

public final class PackageMaterialConverter {
    public PackageMaterialConverter() {}
    public static PackageMaterial convert(PackageMaterialEntity entity) {
        return  PackageMaterial.builder()
                .id(entity.getId())
                .material(entity.getMaterial())
                .build();
    }
    public static PackageMaterialEntity convert(PackageMaterial packageMaterial) {
        return PackageMaterialEntity.builder()
                .id(packageMaterial.getId())
                .material(packageMaterial.getMaterial())
                .build();
    }
}
