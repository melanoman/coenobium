package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTopicAndIdGreaterThan(String topic, Long limit);
    List<Message> findByTopic(String topic);
}
