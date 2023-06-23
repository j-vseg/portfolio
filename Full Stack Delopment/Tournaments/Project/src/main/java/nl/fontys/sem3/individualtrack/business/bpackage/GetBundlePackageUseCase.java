package nl.fontys.sem3.individualtrack.business.bpackage;

import nl.fontys.sem3.individualtrack.domain.BundlePackage;

import java.util.Optional;

public interface GetBundlePackageUseCase {
    Optional<BundlePackage> getBundlePackage(long id);
}
