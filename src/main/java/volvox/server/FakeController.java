package volvox.server;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;
import volvox.beans.GameTable;
import volvox.beans.Text;
import volvox.repository.TextRepository;

@RestController
public class FakeController {

    private static final String template = "Hello, %s!";

    @Autowired
    private TextRepository textRepository;

    @RequestMapping("/ping")
    public Text ping(@RequestParam(value="name", defaultValue="World") String name) {
        Text text = new Text(String.format(template, name));
        textRepository.save(text);
        return text;
    }

    @RequestMapping("/sit")
    public String sit(@RequestParam(value="tableName", defaultValue="Lobby")String tableName) {
        // TODO actually write this method
        return "please implement sit()";
    }

    /**
     * Get a list of tables for the public lobby
     * TODO actually load from the database
     * TODO add a parameter for private lobbies.
     */
    @RequestMapping("/lobby")
    public ModelAndView showLobby() {
        ModelAndView mav = new ModelAndView("lobby");
        mav.addObject("tables", getTables());
        return mav;
    }

    private List<GameTable> getTables() {
        List<GameTable> out = Lists.newArrayList();
        GameTable fake1 = new GameTable();
        fake1.setId(1L);
        GameTable fake2 = new GameTable();
        fake1.setName("First Fake Table");
        fake2.setId(2L);
        fake2.setName("SecondFakeTable");
        out.add(fake1);
        out.add(fake2);
        return out;
    }
}
