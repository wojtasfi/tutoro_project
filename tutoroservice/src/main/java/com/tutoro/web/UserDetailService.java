package com.tutoro.web;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {

    UserDetails loadByUsername(String username) throws UsernameNotFoundException;
}
