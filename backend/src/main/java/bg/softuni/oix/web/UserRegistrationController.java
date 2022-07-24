package bg.softuni.oix.web;

import bg.softuni.oix.service.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

    @ModelAttribute("userModel")
    public UserRegistrationDto initUserModel(){
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String register(){
        return "auth-register";
    }


}
