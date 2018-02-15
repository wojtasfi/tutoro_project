package com.tutoro.service;

import com.tutoro.dao.UserRepository;
import com.tutoro.dto.UserDto;
import com.tutoro.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveNewUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);

    }
}
