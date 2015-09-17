package volvox.server;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import volvox.beans.Message;
import volvox.repository.MessageRepository;

/**
 * This implements a poor-man's idempotent message queue. TODO replace it with a real message queue.
 */
@RestController
public class MessageController {

    @Autowired MessageService messageService;

    /**
     * Retrieve all the messages in a topic since _limit_
     */
    @RequestMapping("/message/get/{topic}/{limit}")
    List<Message> getMessages(@PathVariable("topic")String topicStr, @PathVariable("limit")String limitStr) {
        Long topic = null;
        try {
            topic = Long.parseLong(topicStr);
        } catch (NumberFormatException ex) {
            return messageError("parse failure: topicId", -1L);
        }

        Long limit = null;
        try {
            limit = Long.parseLong(limitStr);
        } catch (NumberFormatException ex) {
            return messageError("parseFailure: idLimit", topic);
        }
        //TODO check topic permissions
        return messageService.readSince(topic, limit);
    }

    /**
     * by convention, message id -1 will be reserved for when we cannot retrieve messages
     */
    private List<Message> messageError(String error, Long topicId) {
        Message msg = new Message();
        msg.setId(-1L);
        msg.setTopicId(topicId);
        Map<String, String> text = Maps.newHashMap();
        text.put("type", "error");
        text.put("msg", error);
        msg.setText(text);
        return Lists.newArrayList(msg);
    }
}
