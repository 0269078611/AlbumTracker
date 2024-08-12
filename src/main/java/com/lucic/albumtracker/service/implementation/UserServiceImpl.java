package com.lucic.albumtracker.service.implementation;

import com.lucic.albumtracker.dto.UserDTO;
import com.lucic.albumtracker.entity.UserEntity;
import com.lucic.albumtracker.entity.enums.Role;
import com.lucic.albumtracker.exception.SignUpException;
import com.lucic.albumtracker.repository.UserRepository;
import com.lucic.albumtracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean existByUsername(String username) {
        return !userRepository.findByUsername(username).isEmpty();
    }
    @Override
    public boolean existByEmail(String email) {
        return !userRepository.findByEmail(email).isEmpty();
    }
    @Override
    public void registerUser(UserDTO user) {
        if (existByEmail(user.getEmail())) {
            throw new SignUpException("Email is already taken");
        }

        if (existByUsername(user.getUsername())) {
            throw new SignUpException("Username is already taken");
        }

        if (!isStrongPassword(user.getPassword())) {
            throw new SignUpException("Password is not strong enough");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setRole(Role.USER);
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(userEntity);
    }
    @Override
    public boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{8,}$";
        return password.matches(passwordRegex);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }
    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }


}
