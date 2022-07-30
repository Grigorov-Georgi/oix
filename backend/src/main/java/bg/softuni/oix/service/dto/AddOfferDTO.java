package bg.softuni.oix.service.dto;

import java.math.BigDecimal;
import java.util.List;

public class AddOfferDTO {
    private String title;
    private String location;
    private String description;
    private BigDecimal price;
    private List<String> categories;
}
