package volvox.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volvox.beans.Message;
import volvox.repository.MessageRepository;

import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepostory;

    public List<Message> readSince(Long topicId, Long lastRead) {
        return messageRepostory.findByTopicIdAndIdGreaterThan(topicId, lastRead);
    }

    public Message postMessage(Long topicId, Map<String, String> text) {
        Message msg = new Message();
        msg.setTopicId(topicId);
        msg.setText(text);
        return messageRepostory.save(msg);
    }
}
