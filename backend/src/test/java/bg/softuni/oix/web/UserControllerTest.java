package bg.softuni.oix.web;

import bg.softuni.oix.annotations.WithMockCustomUser;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void getLoginFormTest() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

    @Test
    @WithMockCustomUser
    void getProfileTest() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("asdasd");
        userEntity.setFirstName("asdasd");
        userEntity.setLastName("asdasd");
        userEntity.setId(1L);
        when(userService.findById(1L)).thenReturn(userEntity);

        mockMvc.perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("my-profile"))
                .andExpect(model().attributeExists("user"));
    }

//    @Test
//    void onFailedLogin() throws Exception {
//        mockMvc.perform(post("/users/login-error")
//                .with(csrf()))
//                .andExpect(flash().attributeExists("bad_credentials"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/users/login"));
//    }



}