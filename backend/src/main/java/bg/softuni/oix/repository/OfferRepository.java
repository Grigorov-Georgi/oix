package bg.softuni.oix.repository;

import bg.softuni.oix.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findAllByBuyerIsNull();

    List<OfferEntity> findAllByBuyerIdEquals(long id);

    List<OfferEntity> findAllBySellerIdEquals(long id);

    List<OfferEntity> findAllByTitle(String title);
}
