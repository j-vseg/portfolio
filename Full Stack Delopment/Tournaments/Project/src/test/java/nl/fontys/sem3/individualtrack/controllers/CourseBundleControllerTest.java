package nl.fontys.sem3.individualtrack.controllers;

import nl.fontys.sem3.individualtrack.business.bundle.*;
import nl.fontys.sem3.individualtrack.controller.bundle.CourseBundleController;
import nl.fontys.sem3.individualtrack.domain.CourseBundle;
import nl.fontys.sem3.individualtrack.persistence.CourseBundleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.ConnectException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseBundleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private CourseBundleRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(CourseBundleRepository.class);
    }

    @MockBean
    private GetAllCourseBundlesUseCase getAllCourseBundleUseCase;
    @MockBean
    private CreateCourseBundleUseCase createCourseBundleUseCase;
    @MockBean
    private GetCourseBundleUseCase getCourseBundleUseCase;
    @MockBean
    private UpdateCourseBundleUseCase updateCourseBundleUseCase;
    @MockBean
    private DeleteCourseBundleUseCase deleteCourseBundleUseCase;

    @Test
    public void getCourseBundle_ShouldReturn201AndResponse_WhenValidRequest() throws Exception {

        /* setup mock */
        CourseBundle courseBundle = new CourseBundle(1L, "Bundle A", "Description", 50.50, 4, Collections.emptyList());
        when(getCourseBundleUseCase.getCourseBundle(1L)).thenReturn(Optional.of(courseBundle));

        mockMvc.perform(get("/bundles/{id}", 1L))
                .andDo(print()).andExpect(status()
                        .isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Bundle A"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.price").value(50.50))
                .andExpect(jsonPath("$.quantity").value(4))
                .andExpect(jsonPath("$.packages").isEmpty());

        verify(getCourseBundleUseCase).getCourseBundle(1L);
        verifyNoInteractions(createCourseBundleUseCase);
    }

    @Test
    void getCourseBundle_ShouldReturnEmptyObjectWhenCourseBundleNotFound() throws Exception {
        mockMvc.perform(get("/bundles/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getCourseBundleUseCase).getCourseBundle(1L);
    }
    @Test
    void getAllBundles_shouldReturn200ResponseWithCourseBundleArray() throws Exception {
       List<CourseBundle> courseBundles =
               List.of(
                        CourseBundle.builder()
                                .id(1L)
                                .name("Bundle A")
                                .description("Description")
                                .price(5.00)
                                .packages(Collections.emptyList())
                                .build(),
                        CourseBundle.builder()
                                .id(2L)
                                .name("Bundle B")
                                .description("Description")
                                .price(5.00)
                                .packages(Collections.emptyList())
                                .build());

        when(getAllCourseBundleUseCase.getBundles()).thenReturn(courseBundles);

        mockMvc.perform(get("/bundles"))
                .andDo(print()).andExpect(status()
                        .isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(""" 
                    {
                        "courseBundles":[
                            {"id":1,"name":"Bundle A","description":"Description", "price":5.00, "packages":[]},
                            {"id":2,"name":"Bundle B","description":"Description", "price":5.00, "packages":[]}
                        ]
                    }
                """));

        verify(getAllCourseBundleUseCase).getBundles(); verifyNoInteractions(createCourseBundleUseCase);
    }

    @Test
    void getAllCourseBundle_ShouldReturnEmptyListWhenCourseBundleNotFound() throws Exception {
        mockMvc.perform(get("/bundles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(""" 
                    {
                        "courseBundles":[]
                    }
                """));

        verify(getAllCourseBundleUseCase).getBundles();
    }

    @Test
    @WithMockUser(username = "janne.vanseggelen", roles = {"Owner"})
    void createCourseBundle_shouldCreateAndReturn201_WhenRequestValid() throws Exception {

        CourseBundle bundle = CourseBundle.builder()
                .name("Bundle A")
                .description("Description")
                .price(5.00)
                .quantity(4)
                .packages(Collections.emptyList())
                .build();

        when(createCourseBundleUseCase.createBundle(bundle)).thenReturn(
                CourseBundle.builder()
                        .id(1L)
                        .name("Bundle A")
                        .description("Description")
                        .quantity(4)
                        .price(5.00)
                        .packages(Collections.emptyList())
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/bundles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        { 
                            "name": "Bundle A", 
                            "description": "Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json
                        (""" 
                            {"bundleId": 1}
                        """)
                );

        verify(createCourseBundleUseCase).createBundle(bundle);
    }

    @Test
    //@WithMockUser(username = "janne.vanseggelen", roles = {"Customer"})
    public void createCourseBundle_ShouldReturn403AndResponse_WhenUserHasInvalidRights() throws Exception {

        CourseBundle courseBundle = new CourseBundle(1L, "Bundle A", "Description", 50.50, 4, Collections.emptyList());
        when(getCourseBundleUseCase.getCourseBundle(1L)).thenReturn(Optional.of(courseBundle));

        mockMvc.perform(MockMvcRequestBuilders.post("/bundles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        { 
                            "name": "Bundle A", 
                            "description": "Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """))
                .andDo(print()).andExpect(status().isForbidden());

    }

    @Test
    void createCourseBundle_shouldCreateAndReturn400_WhenRequestInValid() throws Exception {

        CourseBundle bundle = CourseBundle.builder()
                .name("")
                .description("Description")
                .price(5.00)
                .quantity(4)
                .packages(Collections.emptyList())
                .build();

        when(createCourseBundleUseCase.createBundle(bundle)).thenReturn(
                CourseBundle.builder()
                        .id(1L)
                        .name("")
                        .description("Description")
                        .quantity(4)
                        .price(5.00)
                        .packages(Collections.emptyList())
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/bundles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                        { 
                            "name": "", 
                            "description": "Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "janne.vanseggelen", roles = {"Owner"})
    void updateBundle_ShouldUpdateBundleWhenRequestIsValid() throws Exception {
        CourseBundle bundle = CourseBundle.builder()
                .id(1L)
                .name("Bundle A")
                .description("New Description")
                .quantity(4)
                .price(5.00)
                .packages(null)
                .build();

        doNothing().when(updateCourseBundleUseCase).updateCourseBundle(bundle);

        mockMvc.perform(MockMvcRequestBuilders.put("/bundles/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        { 
                            "id": 1,
                            "name": "Bundle A", 
                            "description": "New Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(updateCourseBundleUseCase).updateCourseBundle(bundle);
    }
    @Test
    void updateBundle_ShouldUpdateBundleWhenRequestIsInValid() throws Exception {
        CourseBundle bundle = CourseBundle.builder()
                .id(1L)
                .name("")
                .description("New Description")
                .price(5.00)
                .packages(Collections.emptyList())
                .build();

        doNothing().when(updateCourseBundleUseCase).updateCourseBundle(bundle);

        mockMvc.perform(MockMvcRequestBuilders.put("/bundles/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
    @Test
    //@WithMockUser(username = "janne.vanseggelen", roles = {"Customer"})
    public void updateCourseBundle_ShouldReturn403AndResponse_WhenUserHasInvalidRights() throws Exception {

        CourseBundle courseBundle = new CourseBundle(1L, "Bundle A", "Description", 50.50, 4, Collections.emptyList());
        when(getCourseBundleUseCase.getCourseBundle(1L)).thenReturn(Optional.of(courseBundle));

        mockMvc.perform(MockMvcRequestBuilders.put("/bundles/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        { 
                            "id": 1,
                            "name": "Bundle A", 
                            "description": "Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """))
                .andDo(print()).andExpect(status().isForbidden());

    }
    @Test
    @WithMockUser(username = "janne.vanseggelen", roles = {"Owner"})
    void deleteBundle_ShouldDeleteBundleWhenRequestIsValid() throws Exception {
        doNothing().when(deleteCourseBundleUseCase).deleteCourseBundle(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/bundles/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        { 
                            "name": "Bundle A", 
                            "description": "New Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteCourseBundleUseCase).deleteCourseBundle(1L);
    }
    @Test
    //@WithMockUser(username = "janne.vanseggelen", roles = {"Customer"})
    public void deleteCourseBundle_ShouldReturn403AndResponse_WhenUserHasInvalidRights() throws Exception {

        CourseBundle courseBundle = new CourseBundle(1L, "Bundle A", "Description", 50.50, 4, Collections.emptyList());
        when(getCourseBundleUseCase.getCourseBundle(1L)).thenReturn(Optional.of(courseBundle));

        mockMvc.perform(MockMvcRequestBuilders.delete("/bundles/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        { 
                            "id": 1,
                            "name": "Bundle A", 
                            "description": "Description",
                            "quantity": 4,
                            "price": 5.00,
                            "packages": []
                        } 
                        """))
                .andDo(print()).andExpect(status().isForbidden());

    }
}
