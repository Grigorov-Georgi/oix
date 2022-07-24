package bg.softuni.oix.service;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.repository.UserRepository;
import bg.softuni.oix.service.dto.UserRegistrationDto;
import bg.softuni.oix.service.mapper.UserMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userMapper = userMapper;
    }

    public void registerAndLogin(UserRegistrationDto userRegistrationDto){
        UserEntity newUser = userMapper.toEntity(userRegistrationDto);
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
}
