package volvox.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import volvox.beans.GameTable;
import volvox.repository.GameTableRepository;
import volvox.repository.SeatingRepository;

/**
 * This controller is responsible for CRUD of tables including un/seating humans and bots. It is *not*
 * responsible for messaging at the table nor journaling messages for offline humans
 */
@RestController
public class TableController {
    @Autowired GameTableRepository tables;
    @Autowired SeatingRepository seats;

    @RequestMapping("/table/list")
    public ModelAndView showTables() {
        ModelAndView mav = new ModelAndView("lobby");
        mav.addObject("tables", tables.findAll());
        GameTable newTable = new GameTable();
        newTable.setName("New Table Name");
        mav.addObject("gameTable", newTable);
        return mav;
    }

    @RequestMapping(value = "/table/add", method = RequestMethod.POST)
    public ModelAndView addTable(@ModelAttribute GameTable gameTable) {
        if (gameTable == null || gameTable.getName() == null || gameTable.getName().length() < 1) {
            return tableError("Cannot create table", "Table Name is required");
        }
        List<GameTable> old = tables.findByName(gameTable.getName());
        // TODO allow same name in different lobby
        if (old.size() > 0) return tableError("Cannot create table", "Table already exists");

        GameTable table = new GameTable();
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
        List<GameTable> victim = tables.findById(id);
        if(victim.size() > 0) tables.delete(victim.get(0));
        else return tableError("Cannot delete table", "Table does not exist");
        ModelAndView mav = new ModelAndView("lobby");
        mav.addObject("tables", tables.findAll());
        GameTable blankTable = new GameTable();
        blankTable.setName("r00lage");
        mav.addObject("gameTable", blankTable);

        return mav;
    }

    private ModelAndView tableError(String hdr, String msg) {
        ModelAndView error = new ModelAndView("tableError");
        error.addObject("header", hdr);
        error.addObject("message", msg);
        return error;
    }
}
