package com.lucic.albumtracker.dto;
import com.lucic.albumtracker.entity.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data

public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
