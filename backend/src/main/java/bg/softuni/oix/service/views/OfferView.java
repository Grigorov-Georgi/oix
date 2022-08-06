package bg.softuni.oix.service.views;

import java.math.BigDecimal;

public class OfferView {
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    private String location;
    private String urlPicture;

    public OfferView(Long id, String title, BigDecimal price, String description, String location, String urlPicture) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.location = location;
        this.urlPicture = urlPicture;
    }

    public OfferView() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
