package bg.softuni.oix.web;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.repository.*;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.views.OfferView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    private OfferService offerService;

    private HomeControllerTestData testData;

    @BeforeEach
    void setUp() {
        testData = new HomeControllerTestData(
                locationRepository,
                categoryRepository,
                userRepository,
                userRoleRepository,
                offerRepository
        );
        testData.init();
    }

    @AfterEach
    void tearDown() {
        testData.cleanUp();
    }

    @Test
    void getAboutPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about-page"))
                .andExpect(model().attributeExists("firstThreeOffers"));

        Assertions.assertEquals(3, offerRepository.count());
    }

    @Test
    void getHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("firstThreeOffers"));

        Assertions.assertEquals(3, offerRepository.count());
    }


}