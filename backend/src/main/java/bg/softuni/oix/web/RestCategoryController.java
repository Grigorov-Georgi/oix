package bg.softuni.oix.web;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.mapper.OfferMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class RestCategoryController {
    private CategoryService categoryService;

    public RestCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/fetch", produces = "application/json")
    @ResponseBody
    public List<CategoryEntity> getCategories(){
        return categoryService.getAllCategories();
    }

    //works very poorly
    @GetMapping("/{id}/delete")
    public ResponseEntity<Long> deleteCategory(@PathVariable long id){
        boolean isDeleted = this.categoryService.delete(id);
        if (!isDeleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
