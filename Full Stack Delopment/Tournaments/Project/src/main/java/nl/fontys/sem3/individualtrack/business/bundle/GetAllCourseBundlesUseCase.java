package nl.fontys.sem3.individualtrack.business.bundle;

import nl.fontys.sem3.individualtrack.domain.CourseBundle;

import java.util.List;

public interface GetAllCourseBundlesUseCase {
    List<CourseBundle> getBundles(/*GetAllCourseBundlesRequest request*/);
}
