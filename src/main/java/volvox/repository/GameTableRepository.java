package volvox.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.GameTable;

public interface GameTableRepository extends CrudRepository<GameTable, Long> {
    List<GameTable> findByName(String name);
    List<GameTable> findById(Long id);
}
