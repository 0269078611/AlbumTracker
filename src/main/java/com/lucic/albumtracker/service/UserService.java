package com.lucic.albumtracker.service;

import com.lucic.albumtracker.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    boolean existByUsername(String username);
    boolean existByEmail(String email);

    void registerUser(UserDTO user);
    boolean isStrongPassword(String password);
}
