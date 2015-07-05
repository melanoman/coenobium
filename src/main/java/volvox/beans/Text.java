package volvox.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="text")
public class Text {
    @Id
    @Column(name="id")
    private final long id;

    @Column(name="content")
    private final String content;

    public Text(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
