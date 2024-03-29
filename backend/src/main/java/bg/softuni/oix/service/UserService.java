package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.entity.UserRoleEntity;
import bg.softuni.oix.model.enums.UserRoleEnum;
import bg.softuni.oix.repository.UserRepository;
import bg.softuni.oix.repository.UserRoleRepository;
import bg.softuni.oix.service.dto.EditProfileDTO;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.mapper.UserMapper;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;

    private static final boolean firstUser = true;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserMapper userMapper, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
    }

    public void registerAndLogin(UserRegistrationDto userRegistrationDto){
        UserEntity newUser = userMapper.userRegisterDtoToUserEntity(userRegistrationDto);
//        if (firstUser){
//            UserRoleEntity admin = userRoleRepository.findById(1L).get();
//            UserRoleEntity moderator = userRoleRepository.findById(2L).get();
//            UserRoleEntity user = userRoleRepository.findById(3L).get();
//
//            List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
//            userRoleEntityList.add(admin);
//            userRoleEntityList.add(moderator);
//            userRoleEntityList.add(user);
//            newUser.setUserRoles(userRoleEntityList);
//        }
        UserRoleEntity byUserRole = userRoleRepository.findByUserRole(UserRoleEnum.USER);
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
        userRoleEntityList.add(byUserRole);
        newUser.setUserRoles(userRoleEntityList);

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
                .map(userMapper::userEntityToUserView)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Logged user not found!"));
    }

    public boolean isAdmin(UserEntity userEntity){
        Optional<UserEntity> byId = userRepository.findById(userEntity.getId());
        UserRoleEntity byUserRole = userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);
        return byId.get().getUserRoles().contains(byUserRole);
    }

    public void update(EditProfileDTO editProfileDTO) {
        UserEntity userById = userRepository.findById(editProfileDTO.getId()).get();
        userMapper.updateUserEntity(userById, editProfileDTO);
        userRepository.save(userById);
    }

    public EditProfileDTO findEditProfileDTOById(long id) {
        return userMapper.userEntityToEditProfileDTO(findById(id));
    }

    public boolean emailExistsInDB(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void makeAdmin(Long id) {
        UserRoleEntity adminRole = userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = userRoleRepository.findByUserRole(UserRoleEnum.USER);
        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(userRole);

        UserEntity byId = this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found!"));
        byId.setUserRoles(roles);
        userRepository.save(byId);
    }
}
