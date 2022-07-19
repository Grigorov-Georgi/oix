package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OixUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public OixUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new OixUserDetails(
                userEntity.getId(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getUserRoles()
                .stream()
                .map(this::map)
                .toList()
        );
    }

    private GrantedAuthority map(UserRoleEntity userRole){
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }


}
