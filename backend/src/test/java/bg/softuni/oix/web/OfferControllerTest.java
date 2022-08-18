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

//    @MockBean
//    private OfferService offerService;
//    @MockBean
//    private LocationService locationService;
//    @MockBean
//    private CategoryService categoryService;
//    @MockBean
//    private CommentService commentService;
//    @MockBean
//    private OixUserDetailsService oixUserDetailsService;


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
//        LocationView locationView = new LocationView();
//        locationView.setCity("Aitos");
//        List<LocationView> locationViews = new ArrayList<>();
//        locationViews.add(locationView);
//        locationViews.add(locationView);
//        locationViews.add(locationView);
//        when(locationService.getAllLocations()).thenReturn(locationViews);
//
//        CategoryEntity categoryEntity = new CategoryEntity();
//        categoryEntity.setName(CategoryEnum.CAR);
//        List<CategoryEntity> categoryEntities = new ArrayList<>();
//        categoryEntities.add(categoryEntity);
//        when(categoryService.getAllCategories()).thenReturn(categoryEntities);
    }

    @AfterEach
    void tearDown() {
        testData.cleanUp();
    }

    @Test
    @WithMockCustomUser
    void addOfferPostWithCorrectParamsTest() throws Exception {
        long countBefore = offerRepository.count();

        mockMvc.perform(post("/offers/add")
                .param("title", "Title")
                .param("location", "Sofia")
                .param("category", "CAR")
                .param("description", "description")
                .param("price", "100")
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
        long count = offerRepository.count();
//        Optional<OfferEntity> byId = offerRepository.findById(count);

        mockMvc.perform(get("/offers/" + count + "/edit"))
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
        long count = offerRepository.count();
        OfferEntity oldEntity = offerRepository.findById(count).get();

        oldEntity.setTitle("Changed");
        oldEntity.setDescription("Changed");
        oldEntity.setUrlPicture("Changed");
        oldEntity.setPrice(BigDecimal.valueOf(99999));

        mockMvc.perform(patch("/offers/" + count + "/edit")
                .param("title", oldEntity.getTitle())
                .param("location", "Sofia")
                .param("category", "CAR")
                .param("description", oldEntity.getDescription())
                .param("price", oldEntity.getPrice().toString())
                .param("urlPicture", oldEntity.getUrlPicture())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));
    }

    @Test
    @WithMockCustomUser
    void postEditOfferWithIncorrectFieldsTest() throws Exception {
        long count = offerRepository.count();
        OfferEntity oldEntity = offerRepository.findById(count).get();

        oldEntity.setTitle("a");
        oldEntity.setDescription("a");
        oldEntity.setUrlPicture("a");
        oldEntity.setPrice(BigDecimal.valueOf(-1));

        mockMvc.perform(patch("/offers/" + count + "/edit")
                .param("title", oldEntity.getTitle())
                .param("location", "Varna")
                .param("category", "Bike")
                .param("description", oldEntity.getDescription())
                .param("price", oldEntity.getPrice().toString())
                .param("urlPicture", oldEntity.getUrlPicture())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/" + count + "/edit"))
                .andExpect(flash().attributeExists("addOfferDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addOfferDTO"));
    }

    @Test
    @WithMockCustomUser
    void deleteOfferTest() throws Exception {
        long beforeCount = offerRepository.count();

        mockMvc.perform(get("/offers/" + beforeCount + "/delete"))
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
        long id = 1;
        mockMvc.perform(get("/offers/1/buy"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));
    }

    @Test
    @WithMockCustomUser
    void getMyOffersTest() throws Exception {
        List<OfferView> offers = initOfferViewList();

//        when(offerService.getMyOffers(1)).thenReturn(offers);
        mockMvc.perform(get("/offers/my"))
                .andExpect(status().isOk())
                .andExpect(view().name("my-offers"))
                .andExpect(model().attributeExists("offers"));
    }

    @Test
    @WithMockCustomUser
    void getBoughtItemsTest() throws Exception {
        List<OfferView> offers = initOfferViewList();

//        when(offerService.getBoughtItems(1)).thenReturn(offers);
        mockMvc.perform(get("/offers/bought-items"))
                .andExpect(status().isOk())
                .andExpect(view().name("bought-items"))
                .andExpect(model().attributeExists("offers"));

//        verify(offerService, times(1)).getBoughtItems(1);
    }

    List<OfferView> initOfferViewList() {
        OfferView offerView = new OfferView();
        offerView.setTitle("AAAAA");
        offerView.setDescription("AAAAAAA");
        offerView.setSellerId(1);
        offerView.setSellerFullName("AAAAA");
        offerView.setId(1L);
        offerView.setCategory("AAAAA");
        offerView.setLocation("AAAAA");
        offerView.setPrice(BigDecimal.valueOf(123));
        offerView.setUrlPicture("asdasd");

        CommentView commentView = new CommentView();
        commentView.setSenderFullName("ASDASD");
        commentView.setDescription("SADASDASD");
        List<CommentView> comments = new ArrayList<>();
        comments.add(commentView);
        comments.add(commentView);
        comments.add(commentView);

        offerView.setComments(comments);

        List<OfferView> offers = new ArrayList<>();
        offers.add(offerView);
        offers.add(offerView);
        return offers;
    }

    @Test
    @WithMockCustomUser
    void postCommentTest() throws Exception {
        mockMvc.perform(post("/offers/1/comment")
                .param("description", "ADSASDASD")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/1/details"));
    }
}