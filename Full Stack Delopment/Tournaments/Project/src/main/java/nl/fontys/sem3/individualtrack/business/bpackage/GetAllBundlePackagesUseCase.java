package nl.fontys.sem3.individualtrack.business.bpackage;

import nl.fontys.sem3.individualtrack.domain.BundlePackage;

import java.util.List;

public interface GetAllBundlePackagesUseCase {
    List<BundlePackage> getPackages(long bundleId);
}
