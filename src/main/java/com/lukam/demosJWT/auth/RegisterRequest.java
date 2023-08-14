package com.lukam.demosJWT.auth;

import java.util.Set;

import com.lukam.demosJWT.user.Group;
import com.lukam.demosJWT.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Group> groups;
}
