package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.business.impl.bundle.CourseBundleConverter;
import nl.fontys.sem3.individualtrack.business.impl.bundle.GetCourseBundleUseCaseImpl;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetCourseBundleUseCaseImplTest {
    private GetCourseBundleUseCaseImpl objectUnderTest;
    private CourseBundleRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(CourseBundleRepository.class);
    }
    @Test
    void GetCourseBundle_HappyFlow() {
        long id = 1;
        CourseBundleEntity courseBundle = CourseBundleEntity.builder()
                .id(id)
                .name("Name")
                .description("Description")
                .price(2.50)
                .packages(Collections.emptyList())
                .build();

        when(repository.findById(id)).thenReturn(Optional.ofNullable(courseBundle));

        objectUnderTest = new GetCourseBundleUseCaseImpl(repository);

        CourseBundle cbExpected = CourseBundle.builder()
                .id(id)
                .name("Name")
                .description("Description")
                .price(2.50)
                .packages(Collections.emptyList())
                .build();
        Optional<CourseBundle> expected = Optional.ofNullable(cbExpected);

        Optional<CourseBundle> result = objectUnderTest.getCourseBundle(id);


        Assertions.assertEquals(expected, result);
    }

    @Test
    void getCourseBundle_ShouldReturnEmptyOptionalWhenCourseBundleNotFound() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        objectUnderTest = new GetCourseBundleUseCaseImpl(repository);

        Optional<CourseBundle> expected = Optional.empty();

        Optional<CourseBundle> result = objectUnderTest.getCourseBundle(1L);

        Assertions.assertEquals(expected, result);
    }
}
