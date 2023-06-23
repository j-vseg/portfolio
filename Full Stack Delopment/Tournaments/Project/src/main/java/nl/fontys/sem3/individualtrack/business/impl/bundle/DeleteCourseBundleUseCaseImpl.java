package nl.fontys.sem3.individualtrack.business.impl.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bundle.DeleteCourseBundleUseCase;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCourseBundleUseCaseImpl implements DeleteCourseBundleUseCase {
    private final CourseBundleRepository repository;
    @Override
    public void deleteCourseBundle(long id) {
        this.repository.deleteById(id);
    }
}
