package volvox.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import volvox.beans.Room;
import volvox.repository.RoomRepository;
import volvox.repository.EntryRepository;

/**
 * This controller is responsible for CRUD of tables including un/seating humans and bots. It is *not*
 * responsible for messaging at the table nor journaling messages for offline humans
 */
@RestController
public class RoomController {
    @Autowired
    RoomRepository tables;
    @Autowired
    EntryRepository seats;

    @RequestMapping("/table/list")
    public ModelAndView showTables() {
        Room newTable = new Room();
        newTable.setName("New Table Name");
        ModelAndView mav = lobbyMAV(newTable);
        return mav;
    }

    @RequestMapping(value = "/table/add", method = RequestMethod.POST)
    public ModelAndView addTable(@ModelAttribute Room gameTable) {
        if (gameTable == null || gameTable.getName() == null || gameTable.getName().length() < 1) {
            return tableError("Cannot create table", "Table Name is required");
        }
        List<Room> old = tables.findByName(gameTable.getName());
        // TODO allow same name in different lobby
        if (old.size() > 0) return tableError("Cannot create table", "Table already exists");

        Room table = new Room();
        table.setName(gameTable.getName());
        table.setLobbyId(-1L);
        tables.save(table);
        ModelAndView mav = new ModelAndView("table");
        mav.addObject("table", table);
        return mav;
    }

    @RequestMapping(value = "/table/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteTable(@PathVariable(value="id") String idstr) {
        long id = Long.parseLong(idstr);
        List<Room> victim = tables.findById(id);
        if(victim.size() > 0) tables.delete(victim.get(0));
        else return tableError("Cannot delete table", "Table does not exist");
        Room blankTable = new Room();
        blankTable.setName("r00lage");
        return lobbyMAV(blankTable);
    }

    private ModelAndView lobbyMAV(Room table) {
        ModelAndView mav = new ModelAndView("lobby");
        mav.addObject("tables", tables.findAll());
        mav.addObject("gameTable", table);

        String name = getName();
        mav.addObject("welcome", "Welcome "+name+"!");
        mav.addObject("isadmin", isAdmin(name));

        return mav;
    }

    private ModelAndView tableError(String hdr, String msg) {
        ModelAndView error = new ModelAndView("tableError");
        error.addObject("header", hdr);
        error.addObject("message", msg);
        return error;
    }

    // TODO make this a public method in some kind of util place
    private String getName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); //get logged in username
    }

    private boolean isAdmin(String name) {
        return "rxx".equals(name) || "mel".equals(name);
    }
}
