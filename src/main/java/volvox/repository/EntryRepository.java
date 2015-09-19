package volvox.repository;

import org.springframework.data.repository.CrudRepository;
import volvox.beans.Entry;

import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {
    public List<Entry> deleteByUserId(Long userId);

    public List<Entry> deleteByRoomIdAndUserId(Long roomId, Long userId);

    public List<Entry> findDistinctByRoomIdAndValidTrue(Long roomId);

    public List<Entry> findDistinctByRoomIdAndValidTrueAndVisibleTrue(Long roomId);

    public List<Entry> findByRoomIdAndUserIdAndValidTrue(Long roomId, Long UserId);
}
