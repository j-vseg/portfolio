package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CreateCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CreateCourseBundleUseCaseImplTest {
    private CourseBundleRepository repository;
    private CreateCourseBundleUseCaseImpl objectUnderTest;
    @BeforeEach
    private void setUp() {
        repository = mock(CourseBundleRepository.class);
    }
    @Test
    void createCourseBundleSuccessfully() {
        CourseBundleEntity courseBundle = CourseBundleEntity.builder()
                .name("Bundle A")
                .description("Description")
                .price(2.50)
                .packages(Collections.emptyList())
                .build();

        CourseBundleEntity returnCourseBundle = CourseBundleEntity.builder()
                .id(1L)
                .name("Bundle A")
                .description("Description")
                .price(2.50)
                .packages(Collections.emptyList())
                .build();


        when(repository.save(courseBundle)).thenReturn(returnCourseBundle);

        objectUnderTest = new CreateCourseBundleUseCaseImpl(repository);

        CourseBundle bundle = CourseBundle.builder()
                .name("Bundle A")
                .description("Description")
                .price(2.50)
                .packages(Collections.emptyList())
                .build();

        CourseBundle actualBundle = objectUnderTest.createBundle(bundle);

        Assertions.assertEquals(returnCourseBundle.getId(), actualBundle.getId());
    }
    @Test
    void createCourseBundle_WhenConvertObjectToEntitySuccessfully() {
        CourseBundleEntity bundleEntity = CourseBundleEntity.builder()
                .id(1L)
                .name("Test Bundle")
                .description("Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();

        CourseBundle result = CourseBundleConverter.convert(bundleEntity);

        Assertions.assertEquals(bundleEntity.getId(), result.getId());
        Assertions.assertEquals(bundleEntity.getName(), result.getName());
        Assertions.assertEquals(bundleEntity.getDescription(), result.getDescription());
        Assertions.assertEquals(bundleEntity.getPrice(), result.getPrice());
        Assertions.assertEquals(bundleEntity.getQuantity(), result.getQuantity());
        Assertions.assertEquals(bundleEntity.getPackages(), result.getPackages());
    }

    @Test
    void createCourseBundle_WhenFailsToConvert_ThrowsNullPointerException() {
        CourseBundle bundle = CourseBundle.builder()
                .name(null)
                .description("Description")
                .price(2.50)
                .packages(Collections.emptyList())
                .build();

        Assertions.assertThrows(NullPointerException.class, () -> objectUnderTest.createBundle(bundle));
    }
}
