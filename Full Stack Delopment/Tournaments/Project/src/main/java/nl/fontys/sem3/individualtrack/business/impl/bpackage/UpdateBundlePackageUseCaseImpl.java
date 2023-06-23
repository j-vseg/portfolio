package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.bpackage.UpdateBundlePackageUseCase;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateBundlePackageUseCaseImpl implements UpdateBundlePackageUseCase {
    private final BundlePackageRepository repository;
    private final CourseBundleRepository bundleRepository;
    private final IdValidator<BundlePackage> idValidator;
    @Override
    public void updateBundlePackage(long bundleId, BundlePackage bundlePackage) {
        if (bundlePackage == null) {
            throw new InvalidException("BUNDLE_PACKAGE_ID_INVALID");
        }

        idValidator.ValidateId(bundlePackage.getId());

        updateFields(bundleId, bundlePackage);
    }
    private void updateFields(long bundleId, BundlePackage bundlePackage) {
        BundlePackageEntity bp = BundlePackageEntity.builder()
                .id(bundlePackage.getId())
                .name(bundlePackage.getName())
                .description(bundlePackage.getDescription())
                .price(bundlePackage.getPrice())
                .courseBundleEntity(bundleRepository.getReferenceById(bundleId))
                .build();
        repository.save(bp);
    }
}
