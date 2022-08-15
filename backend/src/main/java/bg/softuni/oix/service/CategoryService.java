package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAllCategories(){
        return this.categoryRepository.findAll();
    }

    public CategoryEntity findByName(CategoryEnum name){
        return this.categoryRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException("Category with name: " + name + " not found!"));
    }

    public boolean delete(long id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
