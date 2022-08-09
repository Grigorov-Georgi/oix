package bg.softuni.oix.web;

import bg.softuni.oix.config.SecurityConfig;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.views.OfferView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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

    @MockBean
    private OfferService offerService;

    @BeforeEach
    void setUp() {
        List<OfferView> offers;
        OfferView offerView = new OfferView();
        offerView.setTitle("Title");
        offerView.setDescription("Description");

        offers = new ArrayList<>();
        offers.add(offerView);
        offers.add(offerView);
        offers.add(offerView);

        when(offerService.getListWithLastThreeOffers()).thenReturn(offers);
    }

    @Test
    void getAboutPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about-page"))
                .andExpect(model().attributeExists("firstThreeOffers"))
                .andExpect(model().attribute("firstThreeOffers",
                        offerService.getListWithLastThreeOffers()));
    }

    @Test
    void getHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("firstThreeOffers"))
                .andExpect(model().attribute("firstThreeOffers",
                offerService.getListWithLastThreeOffers()));;
    }
}