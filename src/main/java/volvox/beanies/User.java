package volvox.beanies;

import volvox.beans.Bot;
import volvox.beans.Person;

public interface User {
    String getName();

    class PersonUser implements User {
        public final Person person;

        public PersonUser(Person person) { this.person = person; }

        public String getName() { return person.getName(); }
    }

    class BotUser implements User {
        public final Bot bot;

        public BotUser(Bot bot) { this.bot = bot; }

        public String getName() { return bot.getName(); }
    }
}
