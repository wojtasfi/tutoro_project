package com.tutoro.web;

import com.tutoro.dao.TutorRepository;
import com.tutoro.entities.Tutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TutorUserService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(TutorUserService.class);

    private final TutorRepository tutorRepository;

    public TutorUserService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("Loading user");
        Tutor tutor = tutorRepository.findByUsername(username);

        LOGGER.info(tutor.toString());

        if (tutor != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            authorities.add(new SimpleGrantedAuthority("ROLE_TUTOR"));

            User user = new User(tutor.getUsername(), tutor.getPassword(), authorities);
            LOGGER.info(user.toString());
            return user;

        }

        throw new UsernameNotFoundException("User " + username + " not found");
    }

}
