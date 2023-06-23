package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.persistence.entity.BundlePackageEntity;
import nl.fontys.sem3.individualtrack.persistence.entity.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Long> {
    @Query(value = "SELECT b FROM OrderLineEntity b WHERE b.orderEntity.id = :orderId")
    List<OrderLineEntity> findByOrderEntity(long orderId);
}
