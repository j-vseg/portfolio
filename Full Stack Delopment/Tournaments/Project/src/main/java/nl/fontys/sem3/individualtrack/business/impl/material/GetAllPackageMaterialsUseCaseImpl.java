package nl.fontys.sem3.individualtrack.business.impl.material;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.material.GetAllPackageMaterialsUseCase;
import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.PackageMaterialRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.PackageMaterialEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPackageMaterialsUseCaseImpl implements GetAllPackageMaterialsUseCase {
    private PackageMaterialRepository repository;

    @Override
    public List<PackageMaterial> getMaterials(long packageId) {
        List<PackageMaterial> materials = findByPackageId(packageId)
                .stream()
                .map(PackageMaterialConverter::convert)
                .toList();

        return materials;
    }
    private List<PackageMaterialEntity> findByPackageId(long packageId) {
        return repository.findByBundlePackageEntity(packageId);
    }
}
