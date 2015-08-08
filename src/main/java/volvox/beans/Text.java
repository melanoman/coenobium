package volvox.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Text {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String content;

    public Text(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
