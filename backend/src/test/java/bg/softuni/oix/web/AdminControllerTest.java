package bg.softuni.oix.web;

import bg.softuni.oix.annotations.WithMockCustomUser;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.model.enums.UserRoleEnum;
import bg.softuni.oix.repository.*;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.dto.AddLocationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @MockBean
    private CommentRepository commentRepository;

    private GenericControllerTestData testData;

    @BeforeEach
    void setUp() {
        testData = new GenericControllerTestData(
                locationRepository,
                categoryRepository,
                userRepository,
                userRoleRepository,
                offerRepository,
                commentRepository
        );
        testData.init();
    }

    @AfterEach
    void tearDown() {
        testData.cleanUp();
    }

    @Test
    @WithMockCustomUser
    void getAdminPanelTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(model().attributeExists("addLocationModel"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("users"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-panel"));
    }

    @Test
    @WithMockCustomUser
    void deleteLocationTest() throws Exception {
        long countBefore = locationRepository.count();

        mockMvc.perform(get("/admin/location/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));

        long countAfter = locationRepository.count();

        Assertions.assertEquals(countBefore - 1, countAfter);
    }

    @Test
    @WithMockCustomUser
    void postLocationWithCorrectDataTest() throws Exception {
        AddLocationDTO addLocationDTO = new AddLocationDTO();
        addLocationDTO.setCity("Sofia");

        long countBefore = locationRepository.count();

        mockMvc.perform(post("/admin/location/add")
                .param("city", addLocationDTO.getCity())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));

        long countAfter = locationRepository.count();
        Optional<LocationEntity> byId = locationRepository.findById(2l);

        Assertions.assertEquals(countBefore + 1, countAfter);
        Assertions.assertTrue(byId.isPresent());
        Assertions.assertEquals(addLocationDTO.getCity(), byId.get().getCity());
        Assertions.assertEquals(2, byId.get().getId());
    }

    @Test
    @WithMockCustomUser
    void postLocationWithIncorrectDataTest() throws Exception {
        AddLocationDTO addLocationDTO = new AddLocationDTO();
        addLocationDTO.setCity("a");

        long countBefore = locationRepository.count();

        mockMvc.perform(post("/admin/location/add")
                .param("city", addLocationDTO.getCity())
                .with(csrf()))
                .andExpect(flash().attributeExists("addLocationModel"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addLocationModel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));

        long countAfter = locationRepository.count();
        Optional<LocationEntity> byId = locationRepository.findById(2l);

        Assertions.assertEquals(countBefore, countAfter);
        Assertions.assertFalse(byId.isPresent());
    }

    @Test
    @WithMockCustomUser
    void makeUserAdminTest() throws Exception {
        Optional<UserEntity> byIdBefore = userRepository.findByEmail("user@mail.com");
        int beforeSize = byIdBefore.get().getUserRoles().size();
        mockMvc.perform(get("/admin/user/makeAdmin")
                .param("id", byIdBefore.get().getId() + "")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));

        Optional<UserEntity> byIdAfter = userRepository.findByEmail("user@mail.com");
        int afterSize = byIdAfter.get().getUserRoles().size();
        Assertions.assertEquals(beforeSize + 1, afterSize);
    }


}