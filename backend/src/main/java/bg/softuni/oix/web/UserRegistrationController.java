package bg.softuni.oix.web;

import bg.softuni.oix.service.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

//    @ModelAttribute("userModel")
//    public UserRegistrationDto initUserModel(){
//        return new UserRegistrationDto();
//    }

    @GetMapping("/register")
    public String register(){
        return "auth-register";
    }


}
