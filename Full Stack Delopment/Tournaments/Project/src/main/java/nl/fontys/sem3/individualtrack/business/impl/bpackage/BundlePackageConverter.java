package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import nl.fontys.sem3.individualtrack.business.impl.material.PackageMaterialConverter;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;

public final class BundlePackageConverter {
    public BundlePackageConverter() {}
    public static BundlePackage convert(BundlePackageEntity entity) {
        return BundlePackage.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .materials(entity.getMaterials()
                        .stream()
                        .map(PackageMaterialConverter::convert)
                        .toList()
                )
                .build();
    }
    public static BundlePackageEntity convert(BundlePackage bundlePackage) {
        return BundlePackageEntity.builder()
                .id(bundlePackage.getId())
                .name(bundlePackage.getName())
                .description(bundlePackage.getDescription())
                .price(bundlePackage.getPrice())
                .materials(bundlePackage.getMaterials()
                        .stream()
                        .map(PackageMaterialConverter::convert)
                        .toList()
                )
                .build();
    }
}
