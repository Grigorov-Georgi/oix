package bg.softuni.oix.model.entity;

import bg.softuni.oix.model.entity.enums.ConditionEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ManyToOne
    private UserEntity seller;

    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    private int quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionEnum condition;
}
