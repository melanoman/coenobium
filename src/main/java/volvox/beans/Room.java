package volvox.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id; // guaranteed unique
    private String name; // may not be unique
    private long lobbyId;
    private String code;

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

    public long getLobbyId() { return lobbyId; }

    public void setLobbyId(long lobbyId) { this.lobbyId = lobbyId; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }
}
