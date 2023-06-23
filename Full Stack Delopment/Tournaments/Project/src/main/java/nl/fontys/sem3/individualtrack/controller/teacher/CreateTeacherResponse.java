package nl.fontys.sem3.individualtrack.controller.teacher;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTeacherResponse {
    private Long teacherId;
}
