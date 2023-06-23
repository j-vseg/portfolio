package nl.fontys.sem3.individualtrack.business.impl.teacher;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.IdValidator;
import nl.fontys.sem3.individualtrack.business.exception.InvalidException;
import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.persistence.Repository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherIdValidator implements IdValidator<Teacher> {
    private final Repository<Teacher> repository;
    @Override
    public void ValidateId(Long teacherId)  {
        if (teacherId == null || !repository.existsById(teacherId)) {
            throw new InvalidException("TEACHER_ID_INVALID");
        }
    }

}
