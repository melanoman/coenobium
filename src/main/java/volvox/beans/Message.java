package volvox.beans;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long topicId;
    // this is a map so we don't need custom parsers all over from simple stuff
    // example1: { "type" -> "chat", "sender" -> "jeff", "msg" -> "Hello, Derrick" }
    // example2: { "type" -> "move", "from" -> "a2", "to" -> "a4" }
    @ElementCollection
    private Map<String, String> text;

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }
}
