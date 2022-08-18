package bg.softuni.oix.repository;

import bg.softuni.oix.model.entity.OfferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    Page<OfferEntity> findAllByBuyerIsNull(Pageable pageable);

    List<OfferEntity> findAllByBuyerIdEquals(long id);

    List<OfferEntity> findAllBySellerIdEquals(long id);

    List<OfferEntity> findAllByTitleContainingIgnoreCase(String title);

    List<OfferEntity> findAllByBuyerIsNull();

    List<OfferEntity> findAllByBuyerIsNotNull();

    List<OfferEntity> findAllByReleaseDateAfter(LocalDate date);

    Optional<OfferEntity> findByTitle(String title);
}
