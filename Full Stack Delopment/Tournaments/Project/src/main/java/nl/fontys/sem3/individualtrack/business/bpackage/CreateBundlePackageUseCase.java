package nl.fontys.sem3.individualtrack.business.bpackage;

import nl.fontys.sem3.individualtrack.domain.BundlePackage;

public interface CreateBundlePackageUseCase {
    BundlePackage createPackage(long bundleId, BundlePackage bundlePackage);
}
