package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Text;

public interface TextRepository extends CrudRepository<Text, Long> {
}
