package volvox.beans;

public class Text {

    private final long id;
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
