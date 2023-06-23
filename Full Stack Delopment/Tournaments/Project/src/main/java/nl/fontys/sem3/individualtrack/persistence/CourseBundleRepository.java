package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.persistence.entity.CourseBundleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseBundleRepository extends JpaRepository<CourseBundleEntity, Long> {
}
