package nl.fontys.sem3.individualtrack.controller.bpackage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.BundlePackage;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBundlePackagesResponse {
    private List<BundlePackage> bundlePackages;
}
