package nl.fontys.sem3.individualtrack.business.impl.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.material.CreatePackageMaterialUseCase;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.PackageMaterialRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.PackageMaterialEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePackageMaterialUseCaseImpl implements CreatePackageMaterialUseCase {
    private final PackageMaterialRepository repository;
    private final BundlePackageRepository bundlePackageRepository;
    @Override
    public PackageMaterial createMaterial(long packageId, PackageMaterial material) {


        PackageMaterialEntity savedMaterial = saveNewMaterial(packageId, material);

        return PackageMaterialConverter.convert(savedMaterial);
    }
    private PackageMaterialEntity saveNewMaterial(long packageId, PackageMaterial material) {
        PackageMaterialEntity newMaterial = PackageMaterialEntity.builder()
                .material(material.getMaterial())
                .bundlePackageEntity(bundlePackageRepository.getReferenceById(packageId))
                .build();
        repository.save(newMaterial);
        return newMaterial;
    }
}
