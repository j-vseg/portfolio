package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.business.impl.bundle.GetAllCourseBundlesUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllCourseBundlesUseCaseImplTest {
    private GetAllCourseBundlesUseCaseImpl objectUnderTest;
    private CourseBundleRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(CourseBundleRepository.class);
    }

    @Test
    public void GetAllCourseBundles_HappyFlow() {
        CourseBundleEntity cb1 = CourseBundleEntity.builder()
                .id(1L)
                .name("Bundle A")
                .description("Description")
                .price(50.00)
                .packages(Collections.emptyList())
                .build();
        CourseBundleEntity cb2 = CourseBundleEntity.builder()
                .id(2L)
                .name("Bundle B")
                .description("Description")
                .price(50.00)
                .packages(Collections.emptyList())
                .build();

        when(repository.findAll())
                .thenReturn(List.of(cb1, cb2));

        objectUnderTest = new GetAllCourseBundlesUseCaseImpl(repository);

        List<CourseBundle> result = objectUnderTest.getBundles();

        List<CourseBundle> expected = new ArrayList<>();
        expected.add(CourseBundleConverter.convert(cb1));
        expected.add(CourseBundleConverter.convert(cb2));

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void GetAllCourseBundles_WhenBundlesAreEmpty_ReturnsEmptyList() {

        when(repository.findAll())
                .thenReturn(Collections.emptyList());

        objectUnderTest = new GetAllCourseBundlesUseCaseImpl(repository);

        List<CourseBundle> result = objectUnderTest.getBundles();

        List<CourseBundle> expected = Collections.emptyList();

        Assertions.assertEquals(expected, result);
    }
}
