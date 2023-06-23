package nl.fontys.sem3.individualtrack.business.impl.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseBundleIdValidatorImpl implements IdValidator<CourseBundle> {
    private final CourseBundleRepository repository;
    @Override
    public void ValidateId(Long bundleID)  {
        if (bundleID == null || !repository.existsById(bundleID)) {
            throw new InvalidException("COURSE_BUNDLE_ID_INVALID");
        }
    }
}
