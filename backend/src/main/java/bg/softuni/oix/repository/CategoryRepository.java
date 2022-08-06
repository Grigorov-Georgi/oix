package bg.softuni.oix.repository;

import bg.softuni.oix.model.entity.CategoryEntity;
import bg.softuni.oix.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryEnum name);
}
