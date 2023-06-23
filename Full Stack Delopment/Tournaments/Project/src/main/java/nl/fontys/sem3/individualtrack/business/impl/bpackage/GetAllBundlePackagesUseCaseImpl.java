package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bpackage.GetAllBundlePackagesUseCase;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllBundlePackagesUseCaseImpl implements GetAllBundlePackagesUseCase {
    private final BundlePackageRepository repository;
    @Override
    public List<BundlePackage> getPackages(long bundleId) {
        List<BundlePackage> bundlePackages = findAll(bundleId)
                .stream()
                .map(BundlePackageConverter::convert)
                .toList();

        return bundlePackages;
    }
    private List<BundlePackageEntity> findAll(long bundleId) {
        return repository.findByCourseBundleEntity(bundleId);
    }
}
