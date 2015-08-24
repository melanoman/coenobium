package volvox.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Table {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id; // guaranteed unique
    private String name; // may not be unique

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
