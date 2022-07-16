package bg.softuni.oix.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "closed_offers")
public class ClosedOfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UserEntity seller;

    private UserEntity buyer;

    private LocalDate date;
}
