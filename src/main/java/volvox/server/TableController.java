package volvox.server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
            return tableError("Table Name is required");
        }
        List<GameTable> old = tables.findByName(gameTable.getName());
        if (old.size() > 0) return tableError("Table already exists");

        GameTable table = new GameTable();
        table.setName(gameTable.getName());
        table.setLobbyId(-1L);
        tables.save(table);
        ModelAndView mav = new ModelAndView(("table"));
        mav.addObject("table", table);
        return mav;
    }

    private ModelAndView tableError(String msg) {
        ModelAndView error = new ModelAndView("tableError");
        error.addObject("message", msg);
        return error;
    }
}
