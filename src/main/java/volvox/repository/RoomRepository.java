package volvox.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findByName(String name);
    List<Room> findById(Long id);
}
