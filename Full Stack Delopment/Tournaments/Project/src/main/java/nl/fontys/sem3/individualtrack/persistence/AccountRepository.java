package nl.fontys.sem3.individualtrack.persistence;

import nl.fontys.sem3.individualtrack.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query(value = "select a from AccountEntity a where a.username = :username")
    Optional<AccountEntity> findByUsername(String username);
}
