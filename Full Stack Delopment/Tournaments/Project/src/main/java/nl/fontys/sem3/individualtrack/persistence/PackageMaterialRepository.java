package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.domain.PackageMaterial;
import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.PackageMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackageMaterialRepository extends JpaRepository<PackageMaterialEntity, Long> {
    @Query("SELECT b FROM PackageMaterialEntity AS b WHERE b.bundlePackageEntity.id = :packageId")
    List<PackageMaterialEntity> findByBundlePackageEntity(long packageId);
}
