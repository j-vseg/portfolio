package nl.fontys.sem3.individualtrack.business.impl.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bundle.GetAllCourseBundlesUseCase;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllCourseBundlesUseCaseImpl implements GetAllCourseBundlesUseCase {
    private CourseBundleRepository repository;
    @Override
    public List<CourseBundle> getBundles(/*GetAllCourseBundlesRequest request*/) {
        List<CourseBundle> courseBundles = findAll()
                .stream()
                .map(CourseBundleConverter::convert)
                .toList();
        return courseBundles;
    }

    private List<CourseBundleEntity> findAll() {
        return repository.findAll();
    }
}