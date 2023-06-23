package nl.fontys.sem3.individualtrack.controller.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.material.*;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.controller.material.CreatePackageMaterialRequest;
import nl.fontys.sem3.individualtrack.controller.material.CreatePackageMaterialResponse;
import nl.fontys.sem3.individualtrack.controller.material.GetAllPackageMaterialsResponse;
import nl.fontys.sem3.individualtrack.controller.material.UpdatePackageMaterialRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/bundles/{bundleId}/packages/{packageId}/materials")
public class PackageMaterialsController {
    private final CreatePackageMaterialUseCase createPackageMaterialUseCase;
    private final GetAllPackageMaterialsUseCase getAllPackageMaterialsUseCase;
    private final GetPackageMaterialUseCase getPackageMaterialUseCase;
    private final UpdatePackageMaterialUseCase updatePackageMaterialUseCase;
    private final DeletePackageMaterialUseCase deletePackageMaterialUseCase;
    @RolesAllowed({"ROLE_Owner"})
    @PostMapping()
    public ResponseEntity<CreatePackageMaterialResponse> createMaterial(@PathVariable(value = "bundleId") long bundleId,
                                                                        @PathVariable(value = "packageId") long packageId,
                                                                        @RequestBody @Valid CreatePackageMaterialRequest request) {
        PackageMaterial material = PackageMaterial.builder()
                .material(request.getMaterial())
                .build();
        CreatePackageMaterialResponse response = CreatePackageMaterialResponse.builder()
                .materialId(createPackageMaterialUseCase.createMaterial(packageId, material).getId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping()
    public ResponseEntity<GetAllPackageMaterialsResponse> getAllMaterials(@PathVariable(value = "bundleId") long bundleId,
                                                                          @PathVariable(value = "packageId") long packageId) {
        GetAllPackageMaterialsResponse response = GetAllPackageMaterialsResponse.builder()
                .packageMaterials(getAllPackageMaterialsUseCase.getMaterials(packageId))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<PackageMaterial> getMaterial(@PathVariable(value = "bundleId") long bundleId,
                                                       @PathVariable(value = "packageId") long packageId,
                                                      @PathVariable(value = "id") long id) {
        final Optional<PackageMaterial> packageMaterial = getPackageMaterialUseCase.getMaterial(id);
        if (packageMaterial.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(packageMaterial.get());
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateMaterial(@PathVariable(value = "bundleId") long bundleId,
                                               @PathVariable(value = "packageId") long packageId,
                                               @PathVariable(value = "id") long id,
                                               @RequestBody @Valid UpdatePackageMaterialRequest request) {
        request.setId(id);
        PackageMaterial material = PackageMaterial.builder()
                .id(request.getId())
                .material(request.getMaterial())
                .build();
        updatePackageMaterialUseCase.updateMaterial(packageId, material);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed({"ROLE_Owner"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourseBundle(@PathVariable(value = "bundleId") long bundleId,
                                                   @PathVariable(value = "packageId") long packageId,
                                                   @PathVariable int id) {
        deletePackageMaterialUseCase.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
