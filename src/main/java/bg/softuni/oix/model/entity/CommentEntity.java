package bg.softuni.oix.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private String sender;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
