package bg.softuni.oix.web;

import bg.softuni.oix.annotations.WithMockCustomUser;
import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.repository.*;
import bg.softuni.oix.service.*;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.CommentView;
import bg.softuni.oix.service.views.LocationView;
import bg.softuni.oix.service.views.OfferView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class OfferControllerTest {

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
    @Transactional
    void addOfferPostWithCorrectParamsTest() throws Exception {
        long countBefore = offerRepository.count();

        mockMvc.perform(post("/offers/add")
                .param("title", "Title")
                .param("location", "Sofia")
                .param("category", "CAR")
                .param("description", "description")
                .param("price", BigDecimal.valueOf(100).toString())
                .param("urlPicture", "urlPicture")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));

        long countAfter = offerRepository.count();
        Optional<OfferEntity> createdOffer = offerRepository.findByTitle("Title");

        assertEquals(countBefore + 1, countAfter);
        assertTrue(createdOffer.isPresent());
        assertEquals("Title", createdOffer.get().getTitle());
        assertEquals("Sofia", createdOffer.get().getLocation().getCity());
        assertEquals("CAR", createdOffer.get().getCategory().getName().name());
        assertEquals("description", createdOffer.get().getDescription());
        assertEquals("100.00", createdOffer.get().getPrice().toString());
        assertEquals("urlPicture", createdOffer.get().getUrlPicture());
    }

    @Test
    @WithMockCustomUser
    void addOfferPostWithIncorrectParamsTest() throws Exception {
        long countBefore = offerRepository.count();

        mockMvc.perform(post("/offers/add")
                .param("title", "Ti")
                .param("location", "Sofia")
                .param("category", "CAR")
                .param("description", "description")
                .param("price", "100")
                .param("urlPicture", "urlPicture")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/add"))
                .andExpect(flash().attributeExists("addOfferDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addOfferDTO"));

        long countAfter = offerRepository.count();
        Optional<OfferEntity> createdOffer = offerRepository.findByTitle("Title");

        assertEquals(countBefore, countAfter);
        assertFalse(createdOffer.isPresent());
    }

    @Test
    @WithMockCustomUser
    void addOfferGetTest() throws Exception {
        mockMvc.perform(get("/offers/add"))
                .andExpect(model().attributeExists("addOfferDTO"))
                .andExpect(model().attributeExists("commentDTO"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-offer"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void getOfferDetailsTest() throws Exception {
        Optional<OfferEntity> table = offerRepository.findByTitle("Table");
        long id = table.get().getId();

        mockMvc.perform(get("/offers/" + id + "/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("offer-details"))
                .andExpect(model().attributeExists("offer"));
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void getOfferDetailsWithInvalidIdTest() throws Exception {
        mockMvc.perform(get("/offers/" + Long.MAX_VALUE + "/details"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockCustomUser
    void getEditOfferPageTest() throws Exception {
        OfferEntity offerEntity = offerRepository.findAll().get(1);

        mockMvc.perform(get("/offers/" + offerEntity.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-offer"))
                .andExpect(model().attributeExists("addOfferDTO"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    @WithMockCustomUser
    void getEditOfferPageWithInvalidIdTest() throws Exception {
        mockMvc.perform(get("/offers/" + Long.MAX_VALUE + "/edit"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void postEditOfferWithCorrectFieldsTest() throws Exception {
        OfferEntity oldEntity = offerRepository.findAll().get(0);

        oldEntity.setTitle("Changed");
        oldEntity.setDescription("Changed");
        oldEntity.setUrlPicture("Changed");
        oldEntity.setPrice(BigDecimal.valueOf(99999));

        mockMvc.perform(patch("/offers/" + oldEntity.getId() + "/edit")
                .param("title", oldEntity.getTitle())
                .param("location", "Sofia")
                .param("category", "CAR")
                .param("description", oldEntity.getDescription())
                .param("price", oldEntity.getPrice().toString())
                .param("urlPicture", oldEntity.getUrlPicture())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));

        OfferEntity newEntity = offerRepository.findById(oldEntity.getId()).get();
        Assertions.assertEquals(oldEntity.getTitle(), newEntity.getTitle());
        Assertions.assertEquals(oldEntity.getDescription(), newEntity.getDescription());
        Assertions.assertEquals(oldEntity.getUrlPicture(), newEntity.getUrlPicture());
        Assertions.assertEquals(oldEntity.getLocation().getCity(), newEntity.getLocation().getCity());
        Assertions.assertEquals(oldEntity.getCategory().getName(), newEntity.getCategory().getName());
        Assertions.assertEquals(oldEntity.getPrice(), newEntity.getPrice());
    }

    @Test
    @WithMockCustomUser
    void postEditOfferWithIncorrectFieldsTest() throws Exception {
        OfferEntity oldEntity = offerRepository.findAll().get(0);

        oldEntity.setTitle("a");
        oldEntity.setDescription("a");
        oldEntity.setUrlPicture("a");
        oldEntity.setPrice(BigDecimal.valueOf(-1));

        mockMvc.perform(patch("/offers/" + oldEntity.getId() + "/edit")
                .param("title", oldEntity.getTitle())
                .param("location", "Varna")
                .param("category", "Bike")
                .param("description", oldEntity.getDescription())
                .param("price", oldEntity.getPrice().toString())
                .param("urlPicture", oldEntity.getUrlPicture())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/" + oldEntity.getId() + "/edit"))
                .andExpect(flash().attributeExists("addOfferDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addOfferDTO"));
    }

    @Test
    @WithMockCustomUser
    void deleteOfferTest() throws Exception {
        long beforeCount = offerRepository.count();
        OfferEntity offerEntity = offerRepository.findAll().get(1);

        mockMvc.perform(get("/offers/" + offerEntity.getId() + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));

        long afterCount = offerRepository.count();

        assertEquals(beforeCount - 1, afterCount);
    }

    @Test
    @WithMockCustomUser
    void deleteOfferWithInvalidIdTest() throws Exception {
        long beforeCount = offerRepository.count();

        mockMvc.perform(get("/offers/" + Long.MAX_VALUE + "/delete"))
                .andExpect(status().isNotFound());

        long afterCount = offerRepository.count();

        assertEquals(beforeCount, afterCount);
    }

    @Test
    @WithMockCustomUser
    void buyOfferTest() throws Exception {
        OfferEntity offerEntity = offerRepository.findAll().get(0);

        mockMvc.perform(get("/offers/" + offerEntity.getId() + "/buy"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));

        Optional<OfferEntity> byId = offerRepository.findById(offerEntity.getId());
        assertNotNull(byId.get().getBuyer());
    }

    @Test
    @WithMockCustomUser
    void buyOfferWithInvalidIdTest() throws Exception {
        mockMvc.perform(get("/offers/" + Long.MAX_VALUE + "/buy"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockCustomUser
    void getMyOffersTest() throws Exception {
        mockMvc.perform(get("/offers/my"))
                .andExpect(status().isOk())
                .andExpect(view().name("my-offers"))
                .andExpect(model().attributeExists("offers"));
    }

    @Test
    @WithMockCustomUser
    void getBoughtItemsTest() throws Exception {
        mockMvc.perform(get("/offers/bought-items"))
                .andExpect(status().isOk())
                .andExpect(view().name("bought-items"))
                .andExpect(model().attributeExists("offers"));
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void postCommentTest() throws Exception {
        OfferEntity offerEntity = offerRepository.findAll().get(0);
        int sizeBefore = offerEntity.getComments().size();
        long before = commentRepository.count();

        mockMvc.perform(post("/offers/" + offerEntity.getId() + "/comment")
                .param("description", "ADSASDASD")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/" + offerEntity.getId() + "/details"));

        int sizeAfter = offerRepository.findById(offerEntity.getId()).get().getComments().size();
        long after = commentRepository.count();
        assertEquals(before + 1, after);
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void postCommentWithOfferIdTest() throws Exception {
        mockMvc.perform(post("/offers/" + Long.MAX_VALUE + "/comment")
                .param("description", "ADSASDASD")
                .with(csrf()))
                .andExpect(status().isNotFound());
    }
}