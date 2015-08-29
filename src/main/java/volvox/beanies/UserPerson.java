package volvox.beanies;

import volvox.beans.Person;

public class UserPerson implements User {
    public final Person person;

    public UserPerson(Person person) { this.person = person; }

    public String getName() { return person.getName(); }
}
