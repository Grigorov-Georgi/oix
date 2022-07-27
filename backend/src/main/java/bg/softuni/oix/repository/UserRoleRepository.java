package bg.softuni.oix.repository;

import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByUserRole(UserRoleEnum userRole);

    Optional<UserRoleEntity> findById(Long id);
}
