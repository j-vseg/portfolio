package nl.fontys.sem3.individualtrack.business.impl.bundle;

import nl.fontys.sem3.individualtrack.business.impl.bpackage.BundlePackageConverter;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;

public final class CourseBundleConverter {
    public CourseBundleConverter() {}
    public static CourseBundle convert(CourseBundleEntity entity) {
        return  CourseBundle.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .packages(entity.getPackages()
                        .stream()
                        .map(BundlePackageConverter::convert)
                        .toList()
                )
                .build();
    }
    public static CourseBundleEntity convert(CourseBundle courseBundle) {
        return CourseBundleEntity.builder()
                .id(courseBundle.getId())
                .name(courseBundle.getName())
                .description(courseBundle.getDescription())
                .price(courseBundle.getPrice())
                .quantity(courseBundle.getQuantity())
                .packages(courseBundle.getPackages()
                        .stream()
                        .map(BundlePackageConverter::convert)
                        .toList()
                )
                .build();
    }
}
