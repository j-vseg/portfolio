package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.domain.Order;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    boolean existsById(long id);
    T save(T target);

    void deleteById(long id);
    List<T> findAll();
    Optional<T> findById(long id);
}
