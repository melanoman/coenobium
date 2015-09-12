package volvox.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return mav;
    }

    @RequestMapping(value = "/table/add")
    public ModelAndView addTable(@RequestParam("name") String name) {
        GameTable table = new GameTable();
        table.setName(name);
        table.setLobbyId(-1L);
        tables.save(table);
        ModelAndView mav = new ModelAndView(("table"));
        mav.addObject("table", table);
        return mav;
    }
}
