package nl.fontys.sem3.individualtrack.business.impl.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bundle.CreateCourseBundleUseCase;
import nl.fontys.sem3.individualtrack.business.impl.bpackage.BundlePackageConverter;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCourseBundleUseCaseImpl implements CreateCourseBundleUseCase {
    private final CourseBundleRepository repository;
    //private final CourseBundleIdValidator courseBundleIdValidator;
    @Override
    public CourseBundle createBundle(CourseBundle courseBundle) {
        /*if (courseBundleRepository.existsById(request.getId())) {
            throw new IdAlreadyExistsException();
        }
        bundleIdValidator.ValidateId(request.getId()); */

        CourseBundleEntity savedBundle = saveNewBundle(courseBundle);

        return CourseBundleConverter.convert(savedBundle);
    }

    private CourseBundleEntity saveNewBundle(CourseBundle courseBundle) {
        CourseBundleEntity newCourseBundle = CourseBundleEntity.builder()
                //.id(request.getId())
                .name(courseBundle.getName())
                .description(courseBundle.getDescription())
                .price(courseBundle.getPrice())
                .quantity(courseBundle.getQuantity())
                .packages(courseBundle.getPackages()
                        .stream()
                        .map(BundlePackageConverter::convert)
                        .toList())
                .build();
        CourseBundleEntity savedEntity =  repository.save(newCourseBundle);
        return savedEntity;
    }
}
