package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BundlePackageIdValidatorImpl implements IdValidator<BundlePackage> {
    private final BundlePackageRepository repository;
    @Override
    public void ValidateId(Long packageId)  {
        if (packageId == null || !repository.existsById(packageId)) {
            throw new InvalidException("PACKAGE_ID_INVALID");
        }
    }
}
