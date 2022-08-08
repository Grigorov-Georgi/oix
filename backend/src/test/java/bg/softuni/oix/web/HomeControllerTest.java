package bg.softuni.oix.web;

import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.views.OfferView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

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


//    @BeforeEach
//    void setUp(){
//        homeController.initThreeOffers();
//    }


    @Test
    void testHomePage() throws Exception {
        OfferView offerView = new OfferView();
        offerView.setTitle("ASDASD");
        offerView.setDescription("ASDASD");
        List<OfferView> offers = new ArrayList<>();
        offers.add(offerView);
        offers.add(offerView);
        offers.add(offerView);

        mockMvc.perform(get("/").flashAttr("firstThreeOffers", offers))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testAboutPage() throws Exception {
        OfferView offerView = new OfferView();
        offerView.setTitle("ASDASD");
        offerView.setDescription("ASDASD");
        List<OfferView> offers = new ArrayList<>();
        offers.add(offerView);
        offers.add(offerView);
        offers.add(offerView);
        mockMvc.perform(get("/about").flashAttr("firstThreeOffers", offers))
                .andExpect(status().isOk())
                .andExpect(view().name("about-page"));
    }

}