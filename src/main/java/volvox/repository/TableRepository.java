package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Table;

public interface TableRepository extends CrudRepository<Table, Long> {
}
