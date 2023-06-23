package nl.fontys.sem3.individualtrack.business.impl.orderline;

import nl.fontys.sem3.individualtrack.business.impl.bpackage.BundlePackageConverter;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.domain.OrderLine;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;

public class OrderLineConverter {
    public static OrderLine convert(OrderLineEntity entity) {
        return  OrderLine.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .courseBundle(CourseBundleConverter.convert(entity.getCourseBundleEntity()))
                .build();
    }
    public static OrderLineEntity convert(OrderLine orderLine) {
        return OrderLineEntity.builder()
                .id(orderLine.getId())
                .price(orderLine.getPrice())
                .quantity(orderLine.getQuantity())
                .courseBundleEntity(CourseBundleConverter.convert(orderLine.getCourseBundle()))
                .build();
    }
}
