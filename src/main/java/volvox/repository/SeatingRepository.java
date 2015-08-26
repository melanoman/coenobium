package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Seating;

public interface SeatingRepository extends CrudRepository<Seating, Long> {
}
