package nl.fontys.sem3.individualtrack.business.bundle;


import nl.fontys.sem3.individualtrack.business.impl.bundle.DeleteCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.business.impl.bundle.GetCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class DeleteCourseBundleUseCaseImplTest {
    private DeleteCourseBundleUseCaseImpl objectUnderTest;
    private CourseBundleRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(CourseBundleRepository.class);
    }

    @Test
    void deleteBundleSuccessfully() {
        objectUnderTest = new DeleteCourseBundleUseCaseImpl(repository);

        CourseBundleEntity bundleEntity = CourseBundleEntity.builder()
                .id(1L)
                .name("Bundle A")
                .description("Description")
                .price(500)
                .quantity(5)
                .packages(Collections.emptyList())
                .build();
        repository.save(bundleEntity);
        doNothing().when(repository).deleteById(1L);

        try {
            objectUnderTest.deleteCourseBundle(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
    @Test
    void deleteCourseBundle_WhenIdIsInvalid_NothingHappens() {
        objectUnderTest = new DeleteCourseBundleUseCaseImpl(repository);

        try {
            objectUnderTest.deleteCourseBundle(1L);
        }
        catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
