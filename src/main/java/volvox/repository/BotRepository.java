package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Bot;

public interface BotRepository extends CrudRepository<Bot, Long> {
}
