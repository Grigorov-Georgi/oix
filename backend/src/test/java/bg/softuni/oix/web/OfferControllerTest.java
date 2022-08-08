package bg.softuni.oix.web;

import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.CommentService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.OfferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class OfferControllerTest {

    @Mock
    private MockMvc mockMvc;

    @Mock
    private OfferService offerService;
    @Mock
    private LocationService locationService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private CommentService commentService;

    @Test
    void addOfferTest(){

    }
}