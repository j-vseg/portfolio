package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.business.impl.bundle.UpdateCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseBundleConverterTest {
    //private CourseBundleRepository repository;
    private CourseBundleConverter objectUnderTest;

    @BeforeEach
    private void setUp() {
        //repository = mock(CourseBundleRepository.class);
        objectUnderTest = mock(CourseBundleConverter.class);
    }

    @Test
    void ConvertBundleToEntitySuccessfully() {
        CourseBundleEntity bundleEntity = CourseBundleEntity.builder()
                .id(1L)
                .name("Test Bundle")
                .description("Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();

        CourseBundle bundle = CourseBundle.builder()
                .id(1L)
                .name("Test Bundle")
                .description("Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();
        //when(objectUnderTest.convert(bundle)).thenReturn(bundleEntity);

        CourseBundleEntity result = objectUnderTest.convert(bundle);

        Assertions.assertEquals(bundleEntity.getId(), result.getId());
        Assertions.assertEquals(bundleEntity.getName(), result.getName());
        Assertions.assertEquals(bundleEntity.getDescription(), result.getDescription());
        Assertions.assertEquals(bundleEntity.getPrice(), result.getPrice());
        Assertions.assertEquals(bundleEntity.getQuantity(), result.getQuantity());
        Assertions.assertEquals(bundleEntity.getPackages(), result.getPackages());
    }
    @Test
    void ConvertBundleToEntity_WhenEntityIsNull_ThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.convert((CourseBundle) null));
    }

    @Test
    void ConvertToEntityToBundle() {
        CourseBundleEntity bundleEntity = CourseBundleEntity.builder()
                .id(1L)
                .name("Test Bundle")
                .description("Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();

        CourseBundle bundle = CourseBundle.builder()
                .id(1L)
                .name("Test Bundle")
                .description("Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();
        //when(objectUnderTest.convert(bundleEntity)).thenReturn(bundle);

        CourseBundle result = objectUnderTest.convert(bundleEntity);

        Assertions.assertEquals(bundleEntity.getId(), result.getId());
        Assertions.assertEquals(bundleEntity.getName(), result.getName());
        Assertions.assertEquals(bundleEntity.getDescription(), result.getDescription());
        Assertions.assertEquals(bundleEntity.getPrice(), result.getPrice());
        Assertions.assertEquals(bundleEntity.getQuantity(), result.getQuantity());
        Assertions.assertEquals(bundleEntity.getPackages(), result.getPackages());
    }
    @Test
    void ConvertEntityToBundle_WhenEntityIsNull_ThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.convert((CourseBundleEntity) null));
    }
}
