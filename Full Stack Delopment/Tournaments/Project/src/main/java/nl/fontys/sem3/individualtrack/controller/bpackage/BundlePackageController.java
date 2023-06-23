package nl.fontys.sem3.individualtrack.controller.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bpackage.*;
import nl.fontys.sem3.individualtrack.domain.*;
import nl.fontys.sem3.individualtrack.controller.bpackage.CreateBundlePackageRequest;
import nl.fontys.sem3.individualtrack.controller.bpackage.CreateBundlePackageResponse;
import nl.fontys.sem3.individualtrack.controller.bpackage.GetAllBundlePackagesResponse;
import nl.fontys.sem3.individualtrack.controller.bpackage.UpdateBundlePackageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@AllArgsConstructor
@RequestMapping("/bundles/{bundleId}/packages")
public class BundlePackageController {
    private final CreateBundlePackageUseCase createBundlePackageUseCase;
    private final GetAllBundlePackagesUseCase getAllBundlePackagesUseCase;
    private final GetBundlePackageUseCase getBundlePackageUseCase;
    private final UpdateBundlePackageUseCase updateBundlePackageUseCase;
    private final DeleteBundlePackageUseCase deleteBundlePackageUseCase;
    @RolesAllowed({"ROLE_Owner"})
    @PostMapping()
    public ResponseEntity<CreateBundlePackageResponse> createPackage(@PathVariable(value = "bundleId") long id,
                                                                     @RequestBody @Valid CreateBundlePackageRequest request) {
        BundlePackage newPackage = BundlePackage.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .materials(request.getMaterials())
                .build();

        CreateBundlePackageResponse response = CreateBundlePackageResponse.builder()
                .packageId(createBundlePackageUseCase.createPackage(id, newPackage).getId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping()
    public ResponseEntity<GetAllBundlePackagesResponse> getAllPackages(@PathVariable(value = "bundleId") long id) {
        GetAllBundlePackagesResponse response = GetAllBundlePackagesResponse.builder()
                .bundlePackages(getAllBundlePackagesUseCase.getPackages(id))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<BundlePackage> getPackage(@PathVariable(value = "bundleId") long bundleId,
                                                    @PathVariable(value = "id") long id) {
        final Optional<BundlePackage> bundlePackageOptional = getBundlePackageUseCase.getBundlePackage(id);
        if (bundlePackageOptional.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(bundlePackageOptional.get());
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateBundlePackage(@PathVariable(value = "bundleId") long bundleId,
                                                     @PathVariable(value = "id") long id, @RequestBody @Valid UpdateBundlePackageRequest request) {
        request.setId(id);
        BundlePackage bundlePackage = BundlePackage.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        updateBundlePackageUseCase.updateBundlePackage(bundleId, bundlePackage);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed({"ROLE_Owner"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourseBundle(@PathVariable(value = "bundleId") long bundleId,
            @PathVariable int id) {
        deleteBundlePackageUseCase.deleteBundlePackage(id);
        return ResponseEntity.noContent().build();
    }
}
