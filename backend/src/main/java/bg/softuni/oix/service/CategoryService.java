package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAllCategories(){
        return this.categoryRepository.findAll();
    }
}
