package nl.fontys.sem3.individualtrack.business.impl.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bundle.GetCourseBundleUseCase;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCourseBundleUseCaseImpl implements GetCourseBundleUseCase {
    private final CourseBundleRepository repository;
    @Override
    public Optional<CourseBundle> getCourseBundle(long id) {
        return repository.findById(id)
                .map(CourseBundleConverter::convert);
    }

}
