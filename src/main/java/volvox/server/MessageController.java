package volvox.server;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import volvox.beans.Message;
import volvox.beans.Room;
import volvox.beans.StringHolder;
import volvox.repository.MessageRepository;

/**
 * This implements a poor-man's idempotent message queue. TODO replace it with a real message queue.
 */
@RestController
public class MessageController {
    @Autowired MessageService messageService;
    @Autowired SecurityService securityService;

    /**
     * Retrieve all the messages in a topic since _limit_, for AJAX style pages
     */
    @RequestMapping("/message/get/{topic}/{limit}")
    public List<Message> getMessages(@PathVariable("topic")String topic, @PathVariable("limit")String limitStr) {
        Long limit = null;
        try {
            limit = Long.parseLong(limitStr);
        } catch (NumberFormatException ex) {
            // TODO parse from message.properties in display
            return messageError("parseIdLimit", topic);
        }
        //TODO check topic permissions
        return messageService.readSince(topic, limit);
    }

    @RequestMapping(value = "/message/post/{topic}/{room}", method = RequestMethod.POST)
    public ModelAndView postMessage(@PathVariable("topic")String topic, @ModelAttribute("chatText")StringHolder msg, @PathVariable("room")String room) {
        messageService.postChatMessage(securityService.getUsername(), topic, msg.getData());
        return new ModelAndView("redirect:/room/view/"+room);
    }

    /**
     * by convention, message id -1 will be reserved for when we cannot retrieve messages
     */
    private List<Message> messageError(String error, String topic) {
        Message msg = new Message();
        msg.setId(-1L);
        msg.setTopic(topic);
        Map<String, String> text = Maps.newHashMap();
        text.put("type", "error");
        text.put("msg", error);
        msg.setText(text);
        return Lists.newArrayList(msg);
    }
}
