package bg.softuni.oix.web;

import bg.softuni.oix.service.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationDto> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto){
        return null;
    }
}
