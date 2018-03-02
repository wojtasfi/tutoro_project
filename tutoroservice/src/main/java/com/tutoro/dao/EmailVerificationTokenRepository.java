package com.tutoro.dao;

import com.tutoro.entities.EmailVerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by wojci on 4/15/2017.
 */

@Repository
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Long> {

    EmailVerificationToken findByToken(UUID token);

}
