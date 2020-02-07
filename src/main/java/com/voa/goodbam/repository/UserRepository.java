package com.voa.goodbam.repository;

import com.voa.goodbam.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByKakaoId(String kakaoId);
}
