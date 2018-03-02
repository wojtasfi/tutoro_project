package com.tutoro.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotifierClient {
    private static Logger LOGGER = LoggerFactory.getLogger(NotifierClient.class);

    public void sendRegisterVeificationEmail(UUID token) {
        LOGGER.info("Sending verification email");

    }
}
