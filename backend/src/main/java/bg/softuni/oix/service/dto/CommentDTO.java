package bg.softuni.oix.service.dto;

import javax.validation.constraints.Size;

public class CommentDTO {
    @Size(min = 4, max = 200)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
