package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BundlePackageRepository extends JpaRepository<BundlePackageEntity, Long> {
    @Query(value = "SELECT b FROM BundlePackageEntity b WHERE b.courseBundleEntity.id = :bundleId")
    List<BundlePackageEntity> findByCourseBundleEntity(long bundleId);
}
