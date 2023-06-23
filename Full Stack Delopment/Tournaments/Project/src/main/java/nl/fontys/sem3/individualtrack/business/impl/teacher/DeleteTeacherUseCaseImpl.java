package nl.fontys.sem3.individualtrack.business.impl.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.teacher.DeleteTeacherUseCase;
import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTeacherUseCaseImpl implements DeleteTeacherUseCase {
    private final Repository<Teacher> repository;
    @Override
    public void deleteTeacher(long id) {
        this.repository.deleteById(id);
    }
}
