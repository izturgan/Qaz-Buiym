package com.qb.ecommerce.service;

import com.qb.ecommerce.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUser(Long userId);

    Optional<UserDTO> getUserByEmail(String email);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long userId);
}