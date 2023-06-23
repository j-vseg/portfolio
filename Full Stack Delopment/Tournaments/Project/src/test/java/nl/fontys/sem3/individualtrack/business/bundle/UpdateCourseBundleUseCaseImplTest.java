package nl.fontys.sem3.individualtrack.business.bundle;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.IdAlreadyExistsException;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleIdValidatorImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.CreateCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.UpdateCourseBundleUseCaseImpl;
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
import static org.springframework.test.util.AssertionErrors.fail;

public class UpdateCourseBundleUseCaseImplTest {
    private CourseBundleRepository repository;
    private UpdateCourseBundleUseCaseImpl objectUnderTest;
    //private IdValidator<CourseBundle> idValidator;

    @BeforeEach
    private void setUp() {
        repository = mock(CourseBundleRepository.class);
        //idValidator = new CourseBundleIdValidatorImpl(repository);
    }

    @Test
    void UpdateCourseBundleButCourseBundleIsNull() {
        IdValidator<CourseBundle> idValidator = new CourseBundleIdValidatorImpl(repository);
        objectUnderTest = new UpdateCourseBundleUseCaseImpl(repository, idValidator);
        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.updateCourseBundle(null));
    }
    @Test
    void updateCourseBundle_WhenIdInvalid_ThrowInvalidException() {
        IdValidator<CourseBundle> idValidator = new CourseBundleIdValidatorImpl(repository);
        objectUnderTest = new UpdateCourseBundleUseCaseImpl(repository, idValidator);

        CourseBundle bundle = CourseBundle.builder()
                .id(1L)
                .name("Bundle A")
                .description("Description")
                .price(5.00)
                .quantity(4)
                .packages(Collections.emptyList())
                .build();

        Assertions.assertThrows(InvalidException.class, () -> objectUnderTest.updateCourseBundle(bundle));
    }
    @Test
    void updateCourseBundleHappyFlow() {
        CourseBundleEntity bundle = CourseBundleEntity.builder()
                .id(1L)
                .name("Test Bundle")
                .description("Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();
        repository.save(bundle);

        CourseBundle testBundle = CourseBundle.builder()
                .id(1L)
                .name("Test Bundle")
                .description("New Description")
                .price(5.00)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();

        when(repository.save(CourseBundleConverter.convert(testBundle)))
                .thenReturn(CourseBundleConverter.convert(testBundle));
        when(repository.existsById(1L)).thenReturn(true);

        IdValidator<CourseBundle> idValidator = new CourseBundleIdValidatorImpl(repository);
        objectUnderTest = new UpdateCourseBundleUseCaseImpl(repository, idValidator);

        try {
            objectUnderTest.updateCourseBundle(testBundle);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
