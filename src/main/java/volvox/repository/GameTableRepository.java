package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.GameTable;

public interface GameTableRepository extends CrudRepository<GameTable, Long> {
}
