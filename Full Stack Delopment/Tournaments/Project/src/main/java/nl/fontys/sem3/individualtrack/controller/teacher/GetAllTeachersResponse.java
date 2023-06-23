package nl.fontys.sem3.individualtrack.controller.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.Teacher;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTeachersResponse {
    private List<Teacher> teachers;
}
