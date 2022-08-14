package bg.softuni.oix.service.dto;

import bg.softuni.oix.service.dto.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EditProfileDTO {
    private long id;

    @NotBlank(message = "First name is required and should be between 4 and 20 symbols. ")
    @Size(min = 4, max = 20, message = "First name is required and should be between 4 and 20 symbols. ")
    private String firstName;

    @NotBlank(message = "Last name is required and should be between 4 and 20 symbols. ")
    @Size(min = 4, max = 20, message = "Last name is required and should be between 4 and 20 symbols. ")
    private String lastName;

    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
