package bg.softuni.oix.repository;

import bg.softuni.oix.model.entity.CommentEntity;
import bg.softuni.oix.model.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
