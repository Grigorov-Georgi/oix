package bg.softuni.oix.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationPage() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"));
    }

    @Test
    void testUserRegistration() throws Exception {
        MockHttpServletRequestBuilder createRequest =
                post("/users/register").
                        param("firstName", "Anna").
                        param("lastName", "Petrova").
                        param("email", "anna@example.com").
                        param("password", "topsecret").
                        param("confirmPassword", "topsecret").
                        with(csrf());
        mockMvc.perform(createRequest)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testUserRegistrationWithBindingErrors() throws Exception {
        MockHttpServletRequestBuilder createRequest =
                post("/users/register").
                        param("firstName", "Anna").
                        param("lastName", "Petrova").
                        param("email", "").
                        param("password", "topsecret").
                        param("confirmPassword", "topsecret").
                        with(csrf());

        mockMvc.perform(createRequest)
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("userModel"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.userModel"))
                .andExpect(redirectedUrl("/users/register"));
    }
}