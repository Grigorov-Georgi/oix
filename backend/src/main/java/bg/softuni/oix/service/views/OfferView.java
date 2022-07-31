package bg.softuni.oix.service.views;

import java.math.BigDecimal;

public class OfferView {
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;

    public OfferView(Long id, String title, BigDecimal price, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
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
}
