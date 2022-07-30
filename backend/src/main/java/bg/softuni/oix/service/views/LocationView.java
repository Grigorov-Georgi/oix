package bg.softuni.oix.service.views;

public class LocationView {
    private long id;
    private String city;

    public LocationView() {
    }

    public LocationView(long id, String city) {
        this.id = id;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
