package bg.softuni.oix.model.entity;

import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.model.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;
}
