package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.model.enums.UserRoleEnum;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OixUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private OixUserDetailsService toTest;

    @BeforeEach
    void setUp(){
        toTest  = new OixUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_ExistingUser(){
        UserEntity testUserEntity = new UserEntity();
        testUserEntity .setEmail("asd@test.com");
        testUserEntity.setPassword("asdasd");
        testUserEntity.setFirstName("Asen");
        testUserEntity.setLastName("Asenov");

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserRole(UserRoleEnum.USER);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setUserRole(UserRoleEnum.ADMIN);

        List<UserRoleEntity> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        userRoles.add(adminRole);
        testUserEntity.setUserRoles(userRoles);

        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        OixUserDetails userDetails = (OixUserDetails) toTest.loadUserByUsername(testUserEntity.getEmail());

        assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(), userDetails.getFullName());

        List<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities().stream().collect(Collectors.toList());

        assertEquals(2, grantedAuthorities.size());

        assertEquals("ROLE_" + UserRoleEnum.USER.name(), grantedAuthorities.get(0).getAuthority());
        assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(), grantedAuthorities.get(1).getAuthority());
    }

    @Test
    void testLoadUserByUsername_NonExistingUser(){
        assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non@mail.com")
        );
    }

}