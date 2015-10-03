package volvox.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//TODO this isn't going to disk. Is there something other than Entity that would be better to use?
@Entity
public class StringHolder {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
