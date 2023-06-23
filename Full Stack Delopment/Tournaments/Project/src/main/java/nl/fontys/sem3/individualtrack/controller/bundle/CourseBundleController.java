package nl.fontys.sem3.individualtrack.controller.bundle;

import lombok.AllArgsConstructor;
import nl.fontys.sem3.individualtrack.business.bundle.*;
import nl.fontys.sem3.individualtrack.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value = "/bundles") //, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class CourseBundleController {
    private final CreateCourseBundleUseCase createCourseBundleUseCase;
    private final GetAllCourseBundlesUseCase getAllCourseBundlesUseCase;
    private final GetCourseBundleUseCase getCourseBundleUseCase;
    private final UpdateCourseBundleUseCase updateCourseBundleUseCase;
    private final DeleteCourseBundleUseCase deleteCourseBundleUseCase;

    @RolesAllowed({"ROLE_Owner"})
    @PostMapping()
    public ResponseEntity<CreateCourseBundleResponse> createBundle(@RequestBody @Valid CreateCourseBundleRequest request) {
        CourseBundle bundle = CourseBundle.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .packages(request.getPackages())
                .build();

        CreateCourseBundleResponse response = CreateCourseBundleResponse.builder()
                .bundleId(createCourseBundleUseCase.createBundle(bundle).getId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<GetAllCourseBundlesResponse> getAllBundles(@RequestParam(required = false, value = "name") final String name,
                                                                     @RequestParam(required = false, value = "highestPrice") final Long highestPrice,
                                                                     @RequestParam(required = false, value = "lowestPrice") final Long lowestPrice) {
        //GetAllCourseBundlesRequest request = GetAllCourseBundlesRequest.builder().build();
        List<CourseBundle> bundles = getAllCourseBundlesUseCase.getBundles(/*request*/);
        List<CourseBundle> filteredList = new ArrayList<>();
        if (name != null) {
            //bundles.stream().filter(b -> b.getName().equals(name));
            for (CourseBundle b : bundles) {
                if (b.getName().contains(name)) { filteredList.add(b); }
            }
        }
        else if (highestPrice != null) {
            for (CourseBundle b : bundles) {
                if (b.getPrice() < highestPrice) { filteredList.add(b); }
            }
            //bundles.stream().filter(b -> b.getPrice() < highestPrice).collect(Collectors.toList());
            //bundles.removeIf(b -> b.getPrice() > highestPrice);
        }
        else if (lowestPrice != null) {
            //bundles.stream().filter(b -> b.getPrice() > lowestPrice);
            for (CourseBundle b : bundles) {
                if (b.getPrice() > lowestPrice) { filteredList.add(b); }
            }
        }
        else { filteredList = bundles; }

        GetAllCourseBundlesResponse response = GetAllCourseBundlesResponse.builder()
                .courseBundles(filteredList)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<CourseBundle> getCourseBundle(@PathVariable(value = "id") final long id) {
        final Optional<CourseBundle> courseBundleOptional = getCourseBundleUseCase.getCourseBundle(id);
        if (courseBundleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(courseBundleOptional.get());
    }
    @RolesAllowed({"ROLE_Owner", "ROLE_Teacher"})
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourseBundle(@PathVariable("id") long id,
                                                   @RequestBody @Valid UpdateCourseBundleRequest request) {
        request.setId(id);
        CourseBundle courseBundle = CourseBundle.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                        .build();
        updateCourseBundleUseCase.updateCourseBundle(courseBundle);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed({"ROLE_Owner"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCourseBundle(@PathVariable int id) {
        deleteCourseBundleUseCase.deleteCourseBundle(id);
        return ResponseEntity.noContent().build();
    }
}
