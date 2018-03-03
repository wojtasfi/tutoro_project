package com.tutoro.service;

import com.tutoro.clients.NotifierClient;
import com.tutoro.dao.EmailVerificationTokenRepository;
import com.tutoro.dao.UserRepository;
import com.tutoro.dto.UserDto;
import com.tutoro.entities.EmailVerificationToken;
import com.tutoro.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationTokenRepository tokenRepository;

    @Autowired
    private NotifierClient notifierClient;

    public String saveNewUser(UserDto userDto) {

        logger.info("Creating new user: " + userDto.getUsername());
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);

        UUID token = UUID.randomUUID();
        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .username(user.getUsername())
                .token(token)
                .creationDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(30))
                .build();

        tokenRepository.save(emailVerificationToken);

        logger.info("Sending verification email with token: " + token);
        notifierClient.sendRegisterVeificationEmail(token);
        return token.toString();

    }

    public boolean verifyToken(UUID token) {
        EmailVerificationToken emailToken = tokenRepository.findByToken(token);

        if (emailToken == null) {
            return false;
        }
        if (emailToken.getExpirationDate().isBefore(LocalDate.now())) {
            return false;
        }

        logger.info("Veryfing token for user <{}>", emailToken.getUsername());
        User user = userRepository.findOneByUsername(emailToken.getUsername());
        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(emailToken);
        return true;
    }
}
