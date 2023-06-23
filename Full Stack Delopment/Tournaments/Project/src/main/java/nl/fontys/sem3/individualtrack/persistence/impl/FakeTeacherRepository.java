package nl.fontys.sem3.individualtrack.persistence.impl;

import nl.fontys.sem3.individualtrack.domain.Teacher;
import nl.fontys.sem3.individualtrack.persistence.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class FakeTeacherRepository implements Repository<Teacher> {
    private static long NEXT_ID = 1;
    private final List<Teacher> savedTeachers;

    public FakeTeacherRepository() {
        savedTeachers = new ArrayList<>();
    }

    @Override
    public boolean existsById(long id) {
        return this.savedTeachers
                .stream()
                .anyMatch(Teacher -> Teacher.getId().equals(id));
    }

    @Override
    public Teacher save(Teacher teacher) {
        if (teacher.getId() == null) {
            teacher.setId(NEXT_ID);
            NEXT_ID++;
            this.savedTeachers.add(teacher);
        }
        else {
            int index = 0;
            for (int i = 0; i < this.savedTeachers.size(); i++)
                if(savedTeachers.get(i).getId().equals(teacher.getId()))
                    index = i;
            this.savedTeachers.set(index, teacher);
        }
        return teacher;
    }

    @Override
    public void deleteById(long teacherId) {
        this.savedTeachers.removeIf(Account -> Account.getId().equals(teacherId));
    }

    @Override
    public List<Teacher> findAll() {
        return Collections.unmodifiableList((this.savedTeachers));
    }

    @Override
    public Optional<Teacher> findById(long teacherId) {
        return this.savedTeachers.stream()
                .filter(Account -> Account.getId().equals(teacherId))
                .findFirst();
    }
}
