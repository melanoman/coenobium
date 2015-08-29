package volvox.beanies;

import volvox.beans.Bot;

public class UserBot implements User {
    public final Bot bot;

    public UserBot(Bot bot) { this.bot = bot; }

    public String getName() { return bot.getName(); }

}
