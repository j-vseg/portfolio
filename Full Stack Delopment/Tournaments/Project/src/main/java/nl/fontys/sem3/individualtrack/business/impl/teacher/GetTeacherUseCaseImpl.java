package nl.fontys.sem3.individualtrack.business.impl.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.teacher.GetTeacherUseCase;
import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetTeacherUseCaseImpl implements GetTeacherUseCase {
    private final Repository<Teacher> repository;
    @Override
    public Optional<Teacher> getTeacher(long id) {
        return repository.findById(id);
    }
}
