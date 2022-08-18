package bg.softuni.oix.web;

import bg.softuni.oix.annotations.WithMockCustomUser;
import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class RestCategoryControllerTest {
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
    void getCategories() throws Exception {
        mockMvc.perform(get("/categories/fetch"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    void deleteCategory() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(CategoryEnum.OTHER);
        CategoryEntity save = categoryRepository.save(categoryEntity);

        mockMvc.perform(get("/categories/" + save.getId() + "/delete"))
                .andExpect(status().isOk());

        Optional<CategoryEntity> byName = categoryRepository.findByName(CategoryEnum.OTHER);
        assertFalse(byName.isPresent());
    }

    @Test
    @WithMockCustomUser
    void deleteCategoryWithWrongId() throws Exception {
        mockMvc.perform(get("/categories/" + Long.MAX_VALUE + "/delete"))
                .andExpect(status().isNotFound());
    }
}