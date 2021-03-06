package com.authentication.repository;


import com.authentication.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}