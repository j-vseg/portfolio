package nl.fontys.sem3.individualtrack.controller.bundle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCourseBundlesResponse {
    private List<CourseBundle> courseBundles;
}
