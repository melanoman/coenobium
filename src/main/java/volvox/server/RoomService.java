package volvox.server;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import volvox.beans.Entry;
import volvox.beans.User;
import volvox.repository.EntryRepository;
import volvox.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    // TODO separate User from Account and only send User in terse form
    public List<User> findUsersByRoom(Long roomId, boolean xray) {
        List<Entry> entries = xray ?
                entryRepository.findDistinctByRoomIdAndValidTrue(roomId) :
                entryRepository.findDistinctByRoomIdAndValidTrueAndVisibleTrue(roomId);
        List<User> result = Lists.newArrayList();
        for (Entry entry : entries) {
            User user = userRepository.findOne(entry.getUserId());
            if (user != null) result.add(user);
        }
        return result;
    }

    public void enterRoom(Long roomId, Long userId, boolean visible) {
        List<Entry> entries = entryRepository.findByRoomIdAndUserIdAndValidTrue(roomId, userId);
        if (entries.size() > 0) return;

        Entry entry = new Entry();
        entry.setRoomId(roomId);
        entry.setUserId(userId);
        entry.setValid(true);
        entry.setVisible(visible);
        entryRepository.save(entry);
    }

    @Transactional
    public void exitRoom(Long roomId, Long userId) {
        entryRepository.deleteByRoomIdAndUserId(roomId, userId);
    }

    @Transactional
    public void exitAllRooms(Long userId) {
        entryRepository.deleteByUserId(userId);
    }
}
