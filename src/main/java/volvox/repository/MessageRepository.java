package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTopicIdAndIdGreaterThan(Long topic, Long limit);
}
