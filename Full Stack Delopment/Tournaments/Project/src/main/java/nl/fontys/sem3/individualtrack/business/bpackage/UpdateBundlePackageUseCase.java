package nl.fontys.sem3.individualtrack.business.bpackage;

import nl.fontys.sem3.individualtrack.domain.BundlePackage;

public interface UpdateBundlePackageUseCase {
    void updateBundlePackage(long bundleId, BundlePackage bundlePackage);
}
