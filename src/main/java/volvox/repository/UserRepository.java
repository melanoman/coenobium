package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByName(String name);
}
