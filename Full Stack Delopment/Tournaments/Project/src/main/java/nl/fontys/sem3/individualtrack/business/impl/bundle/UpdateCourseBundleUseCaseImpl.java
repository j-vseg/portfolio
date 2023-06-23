package nl.fontys.sem3.individualtrack.business.impl.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.bundle.UpdateCourseBundleUseCase;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCourseBundleUseCaseImpl implements UpdateCourseBundleUseCase {
    private final CourseBundleRepository repository;
    private final IdValidator<CourseBundle> idValidator;
    @Override
    public void updateCourseBundle(CourseBundle courseBundle) {

        if (courseBundle == null) {
            throw new InvalidException("COURSE_BUNDLE_ID_INVALID");
        }

        idValidator.ValidateId(courseBundle.getId());

        updateFields(courseBundle);
    }

    private void updateFields(CourseBundle courseBundle) {
        CourseBundleEntity cb = CourseBundleEntity.builder()
                .id(courseBundle.getId())
                .name(courseBundle.getName())
                .description(courseBundle.getDescription())
                .price(courseBundle.getPrice())
                .quantity(courseBundle.getQuantity())
                .build();

        repository.save(cb);
    }
}
