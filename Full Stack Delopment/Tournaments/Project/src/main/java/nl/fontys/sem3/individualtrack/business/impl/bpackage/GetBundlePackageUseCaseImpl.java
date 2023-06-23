package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bpackage.GetBundlePackageUseCase;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetBundlePackageUseCaseImpl implements GetBundlePackageUseCase {
    private final BundlePackageRepository repository;

    @Override
    public Optional<BundlePackage> getBundlePackage(long id) {
        return repository.findById(id)
                .map(BundlePackageConverter::convert);
    }
}
