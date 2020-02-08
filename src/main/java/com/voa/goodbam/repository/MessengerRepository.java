package com.voa.goodbam.repository;

import com.voa.goodbam.domain.messenger.Messenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessengerRepository extends CrudRepository<Messenger, Long> {
    List<Messenger> findMessengersBySenderIdAndResponseAtIsNotNullOrderByResponseAtDesc(long senderUserId);

    List<Messenger> findMessengersByTargetIdAndResponseAtIsNull(long targetUserId);
}