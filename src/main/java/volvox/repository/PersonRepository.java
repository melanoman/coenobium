package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
