package bg.softuni.oix.web;

import bg.softuni.oix.annotations.WithMockCustomUser;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.dto.AddLocationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LocationService locationService;

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
        when(locationService.delete(1L)).thenReturn(true);

        mockMvc.perform(get("/admin/location/delete")
                .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));
    }

    @Test
    @WithMockCustomUser
    void postLocationWithCorrectDataTest() throws Exception {
        AddLocationDTO addLocationDTO = new AddLocationDTO();
        addLocationDTO.setCity("Sofia");

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity("Sofia");
        locationEntity.setId(1);

        when(locationService.save(addLocationDTO)).thenReturn(locationEntity);

        mockMvc.perform(post("/admin/location/add")
                .param("city", addLocationDTO.getCity())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));
    }

    @Test
    @WithMockCustomUser
    void postLocationWithIncorrectDataTest() throws Exception {
        AddLocationDTO addLocationDTO = new AddLocationDTO();
        addLocationDTO.setCity("a");

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity("a");
        locationEntity.setId(1);

        when(locationService.save(addLocationDTO)).thenReturn(locationEntity);

        mockMvc.perform(post("/admin/location/add")
                .param("city", addLocationDTO.getCity())
                .with(csrf()))
                .andExpect(flash().attributeExists("addLocationModel"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addLocationModel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"));
    }


}