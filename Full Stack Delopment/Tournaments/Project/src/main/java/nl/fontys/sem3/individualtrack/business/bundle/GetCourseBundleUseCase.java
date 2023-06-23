package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.domain.CourseBundle;

import java.util.Optional;

public interface GetCourseBundleUseCase {
    Optional<CourseBundle> getCourseBundle(long id);
}
