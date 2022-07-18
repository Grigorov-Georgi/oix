package bg.softuni.oix.model.entity;

import bg.softuni.oix.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", userRole=" + userRole +
                '}';
    }
}
