package bg.softuni.oix.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class OfferDto {
    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String title;

    @NotBlank
    @Size(min = 4, max = 20)
    private String city;

    @PastOrPresent
    private LocalDate releaseDate;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotBlank
    @Size(min = 4, max = 20)
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
