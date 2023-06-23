package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
