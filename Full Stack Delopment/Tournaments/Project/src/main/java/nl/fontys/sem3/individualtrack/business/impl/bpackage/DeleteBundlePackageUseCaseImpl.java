package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bpackage.DeleteBundlePackageUseCase;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteBundlePackageUseCaseImpl implements DeleteBundlePackageUseCase {
    private final BundlePackageRepository repository;
    @Override
    public void deleteBundlePackage(long id) {
        this.repository.deleteById(id);
    }
}
