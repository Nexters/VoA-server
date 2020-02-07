package com.voa.goodbam.domain.messenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessengerRepository extends CrudRepository<Messenger, Long> {

}