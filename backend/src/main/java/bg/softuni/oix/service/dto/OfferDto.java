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
    private String sellerName;

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

    //private List<CommentDto> comments;

}
