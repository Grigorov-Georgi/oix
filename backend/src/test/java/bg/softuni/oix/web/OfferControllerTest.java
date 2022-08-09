package bg.softuni.oix.web;

import bg.softuni.oix.annotations.WithMockCustomUser;
import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.service.*;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.views.CommentView;
import bg.softuni.oix.service.views.LocationView;
import bg.softuni.oix.service.views.OfferView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @MockBean
    private OfferService offerService;
    @MockBean
    private LocationService locationService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CommentService commentService;
    @MockBean
    private OixUserDetailsService oixUserDetailsService;


    @BeforeEach
    void setUp() {
        LocationView locationView = new LocationView();
        locationView.setCity("Aitos");
        List<LocationView> locationViews = new ArrayList<>();
        locationViews.add(locationView);
        locationViews.add(locationView);
        locationViews.add(locationView);
        when(locationService.getAllLocations()).thenReturn(locationViews);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(CategoryEnum.CAR);
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity);
        when(categoryService.getAllCategories()).thenReturn(categoryEntities);
    }

    @Test
    @WithMockUser(
            authorities = "USER"
    )
    void addOfferPostWithCorrectParamsTest() throws Exception {
        mockMvc.perform(post("/offers/add")
                .param("title", "Title")
                .param("location", "location")
                .param("category", "category")
                .param("description", "description")
                .param("price", "100")
                .param("urlPicture", "urlPicture")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));
    }

    @Test
    @WithMockUser(
            authorities = "USER"
    )
    void addOfferPostWithIncorrectParamsTest() throws Exception {
        mockMvc.perform(post("/offers/add")
                .param("a", "Title")
                .param("location", "location")
                .param("category", "category")
                .param("description", "description")
                .param("price", "100")
                .param("urlPicture", "urlPicture")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/add"))
                .andExpect(flash().attributeExists("addOfferDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addOfferDTO"));
    }

    @Test
    @WithMockUser(
            authorities = "USER"
    )
    void addOfferGetTest() throws Exception {
        mockMvc.perform(get("/offers/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-offer"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    @WithMockCustomUser
    void getOfferDetailsTest() throws Exception {
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
        when(offerService.findViewById(1)).thenReturn(offerView);

        mockMvc.perform(get("/offers/1/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("offer-details"))
                .andExpect(model().attributeExists("offer"));
    }

    @Test
    @WithMockCustomUser
    void getEditOfferPageTest() throws Exception {
        AddOfferDTO offerDto = new AddOfferDTO();
        offerDto.setTitle("AAAAA");
        offerDto.setDescription("AAAAAAA");
        offerDto.setId(1L);
        offerDto.setCategory("AAAAA");
        offerDto.setUrlPicture("AAAAAAA");
        offerDto.setLocation("AAAAAAA");
        offerDto.setPrice(BigDecimal.valueOf(1324));

        when(offerService.findDtoById(1L)).thenReturn(offerDto);

        mockMvc.perform(get("/offers/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-offer"))
                .andExpect(model().attributeExists("updateOfferDTO"))
                .andExpect(model().attributeExists("locations"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    @WithMockCustomUser
    void postEditOfferWithCorrectFieldsTest() throws Exception {
        AddOfferDTO offerDto = new AddOfferDTO();
        offerDto.setTitle("AAAAA");
        offerDto.setDescription("AAAAAAA");
        offerDto.setCategory("AAAAA");
        offerDto.setUrlPicture("AAAAAAA");
        offerDto.setLocation("AAAAAAA");
        offerDto.setPrice(BigDecimal.valueOf(1324));

        when(offerService.findDtoById(1L)).thenReturn(offerDto);

        mockMvc.perform(post("/offers/1/edit")
        .param("title", "AAAAAAA")
        .param("location", "AAAAAAA")
        .param("category", "AAAAAAA")
        .param("description", "AAAAAAA")
        .param("price", "1111")
        .param("urlPicture", "AAAAAAA")
        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));
    }

    @Test
    @WithMockCustomUser
    void postEditOfferWithIncorrectFieldsTest() throws Exception {
        AddOfferDTO offerDto = new AddOfferDTO();
        offerDto.setTitle("AAAAA");
        offerDto.setDescription("AAAAAAA");
        offerDto.setCategory("AAAAA");
        offerDto.setUrlPicture("AAAAAAA");
        offerDto.setLocation("AAAAAAA");
        offerDto.setPrice(BigDecimal.valueOf(1324));

        when(offerService.findDtoById(1L)).thenReturn(offerDto);

        mockMvc.perform(post("/offers/1/edit")
                .param("title", "a")
                .param("location", "AAAAAAA")
                .param("category", "AAAAAAA")
                .param("description", "AAAAAAA")
                .param("price", "1111")
                .param("urlPicture", "AAAAAAA")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers/1/edit"))
        .andExpect(flash().attributeExists("updateOfferDTO"))
        .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.updateOfferDTO"));
    }

    @Test
    @WithMockCustomUser
    void deleteOfferTest() throws Exception {
        long id = 1;
        mockMvc.perform(get("/offers/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/offers"));
        verify(offerService, times(1)).deleteOffer(id);
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

        when(offerService.getMyOffers(1)).thenReturn(offers);
        mockMvc.perform(get("/offers/my"))
                .andExpect(status().isOk())
                .andExpect(view().name("my-offers"))
        .andExpect(model().attributeExists("offers"));
    }

    @Test
    @WithMockCustomUser
    void getBoughtItemsTest() throws Exception {
        List<OfferView> offers = initOfferViewList();

        when(offerService.getBoughtItems(1)).thenReturn(offers);
        mockMvc.perform(get("/offers/bought-items"))
                .andExpect(status().isOk())
                .andExpect(view().name("bought-items"))
                .andExpect(model().attributeExists("offers"));
    }

    List<OfferView> initOfferViewList(){
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
}