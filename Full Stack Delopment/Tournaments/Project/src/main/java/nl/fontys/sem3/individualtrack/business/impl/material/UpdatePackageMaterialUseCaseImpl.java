package nl.fontys.sem3.individualtrack.business.impl.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.business.material.UpdatePackageMaterialUseCase;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.PackageMaterialRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.PackageMaterialEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatePackageMaterialUseCaseImpl implements UpdatePackageMaterialUseCase {
    private final PackageMaterialRepository repository;
    private final BundlePackageRepository packageRepository;
    private final IdValidator<PackageMaterial> idValidator;
    @Override
    public void updateMaterial(long packageId, PackageMaterial material) {
        Optional<PackageMaterial> packageMaterialOptional = repository.findById(material.getId())
                .map(PackageMaterialConverter::convert);

        if (packageMaterialOptional.isEmpty()) {
            throw new InvalidException("MATERIAL_ID_INVALID");
        }

        idValidator.ValidateId(material.getId());

        updateFields(packageId, material);
    }
    private void updateFields(long packageId, PackageMaterial material) {
        PackageMaterialEntity pm = PackageMaterialEntity.builder()
                .id(material.getId())
                .material(material.getMaterial())
                .bundlePackageEntity(packageRepository.getReferenceById(packageId))
                .build();
        repository.save(pm);
    }
}
