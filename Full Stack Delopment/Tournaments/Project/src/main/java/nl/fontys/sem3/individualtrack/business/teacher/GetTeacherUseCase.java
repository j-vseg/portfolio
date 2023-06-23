package nl.fontys.sem3.individualtrack.business.teacher;

import nl.fontys.sem3.individualtrack.domain.Teacher;

import java.util.Optional;

public interface GetTeacherUseCase {
    Optional<Teacher> getTeacher(long id);
}
