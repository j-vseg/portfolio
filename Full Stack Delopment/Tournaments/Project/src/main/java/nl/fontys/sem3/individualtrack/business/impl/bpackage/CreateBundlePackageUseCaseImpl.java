package nl.fontys.sem3.individualtrack.business.impl.bpackage;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bpackage.CreateBundlePackageUseCase;
import nl.fontys.sem3.individualtrack.business.impl.material.PackageMaterialConverter;
import nl.fontys.sem3.individualtrack.domain.*;
import nl.fontys.sem3.individualtrack.persistence.BundlePackageRepository;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateBundlePackageUseCaseImpl implements CreateBundlePackageUseCase {

    private final BundlePackageRepository repository;
    private final CourseBundleRepository courseBundleRepository;

    @Override
    public BundlePackage createPackage(long bundleId, BundlePackage bundlePackage)
    {

        BundlePackageEntity savedBundlePackage = saveNewPackage(bundleId, bundlePackage);

        return BundlePackageConverter.convert(savedBundlePackage);
    }

    private BundlePackageEntity saveNewPackage(long bundleId, BundlePackage bundlePackage) {
        BundlePackageEntity newBundlePackage = BundlePackageEntity.builder()
                //.id(request.getId())
                .name(bundlePackage.getName())
                .description(bundlePackage.getDescription())
                .price(bundlePackage.getPrice())
                .materials(bundlePackage.getMaterials()
                        .stream()
                        .map(PackageMaterialConverter::convert)
                        .toList()
                )
                .courseBundleEntity(courseBundleRepository.getReferenceById(bundleId))
                .build();
        repository.save(newBundlePackage);
        return newBundlePackage;
    }
}
