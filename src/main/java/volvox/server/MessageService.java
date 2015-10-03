package volvox.server;

import com.google.common.collect.Maps;
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

    public List<Message> readSince(String topic, Long lastRead) {
        return messageRepostory.findByTopicAndIdGreaterThan(topic, lastRead);
    }

    public Message postMessage(String topic, Map<String, String> text) {
        Message msg = new Message();
        msg.setTopic(topic);
        msg.setText(text);
        return messageRepostory.save(msg);
    }

    public Message postChatMessage(String username, String topic, String data) {
        Message msg = new Message();
        msg.setTopic(topic);
        Map<String,String> text = Maps.newHashMap();
        text.put("type", "chat");
        text.put("sender", username);
        text.put("msg", data);
        msg.setText(text);
        return messageRepostory.save(msg);
    }
}
