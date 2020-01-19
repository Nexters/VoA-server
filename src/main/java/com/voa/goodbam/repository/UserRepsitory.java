package com.voa.goodbam.repository;

import com.voa.goodbam.domain.room.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepsitory extends CrudRepository<User, Long> {

}
