package com.lucic.albumtracker.service.implementation;

import com.lucic.albumtracker.dto.UserDTO;
import com.lucic.albumtracker.entity.UserEntity;
import com.lucic.albumtracker.entity.enums.Role;
import com.lucic.albumtracker.exception.SignUpException;
import com.lucic.albumtracker.mapper.UserMapper;
import com.lucic.albumtracker.repository.UserRepository;
import com.lucic.albumtracker.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
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


        UserEntity userEntity = userMapper.userDTOToUser(user);
        userEntity.setRole(Role.USER);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(userEntity);
    }
    @Override
    public boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{8,}$";
        return password.matches(passwordRegex);
    }
    @PostConstruct
    public void loadData() {

        long adminCount = userRepository.countByRole(Role.ADMIN);

        if (adminCount == 0) {
            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setEmail("admin@example.com");
            user.setPassword(passwordEncoder.encode("adminpassword")); // Use a secure password
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            System.out.println("Admin user created: admin/adminpassword");
        }
    }




}
