package bg.softuni.oix.web;

import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.service.UserService;
import bg.softuni.oix.service.dto.EditProfileDTO;
import bg.softuni.oix.service.mapper.UserMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/login")
    public String login(){
        return "auth-login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String myProfile(Model model, @AuthenticationPrincipal OixUserDetails oixUserDetails){
        UserEntity user = userService.findById(oixUserDetails.getId());
        model.addAttribute("user", user);
        return "my-profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String getEditProfileView(@PathVariable long id, Model model){
        if (!model.containsAttribute("editProfileDTO")) {
            EditProfileDTO editProfileDTO = userService.findEditProfileDTOById(id);
            model.addAttribute("editProfileDTO", editProfileDTO);
        }
        return "edit-profile";
    }

    @PatchMapping("/profile/{id}/edit")
    public String editProfile(@Valid EditProfileDTO editProfileDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @PathVariable long id,
                              @AuthenticationPrincipal OixUserDetails oixUserDetails){

        if (!editProfileDTO.getEmail().equals(oixUserDetails.getUsername())){
            if (userService.emailExistsInDB(editProfileDTO.getEmail())) {
                ObjectError emailError = new ObjectError("uniqueEmail", "Email is already used. Try another one!");
                bindingResult.addError(emailError);
            }
        }

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editProfileDTO", editProfileDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editProfileDTO", bindingResult);
            return String.format("redirect:/users/profile/%d/edit", id);
        }

        userService.update(editProfileDTO);
        return "redirect:/";
    }
}
