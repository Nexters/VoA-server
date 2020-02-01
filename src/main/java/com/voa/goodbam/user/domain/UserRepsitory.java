package com.voa.goodbam.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsitory extends CrudRepository<User, Long> {

}
