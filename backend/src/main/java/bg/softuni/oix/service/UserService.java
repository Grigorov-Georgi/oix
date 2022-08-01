package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.repository.UserRepository;
import bg.softuni.oix.repository.UserRoleRepository;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.mapper.UserMapper;
import bg.softuni.oix.service.mapper.UserViewMapper;
import bg.softuni.oix.service.views.UserView;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private UserMapper userMapper;
    private UserRoleRepository userRoleRepository;

    private static boolean firstUser = true;
    private UserViewMapper userViewMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserMapper userMapper, UserRoleRepository userRoleRepository, UserViewMapper userViewMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
        this.userViewMapper = userViewMapper;
    }

    public void registerAndLogin(UserRegistrationDto userRegistrationDto){
        UserEntity newUser = userMapper.toEntity(userRegistrationDto);
        if (firstUser){
            UserRoleEntity admin = userRoleRepository.findById(1L).get();
            UserRoleEntity moderator = userRoleRepository.findById(2L).get();
            UserRoleEntity user = userRoleRepository.findById(3L).get();

            List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
            userRoleEntityList.add(admin);
            userRoleEntityList.add(moderator);
            userRoleEntityList.add(user);
            newUser.setUserRoles(userRoleEntityList);
        }
        newUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));

        this.userRepository.save(newUser);
        login(newUser);
    }

    public void login(UserEntity userEntity){
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public List<UserView> getAllUsersForAdminPanel(){
        return this.userRepository.findAll().stream()
                .map(u -> userViewMapper.toDto(u))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}
