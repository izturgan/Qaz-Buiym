package com.qb.ecommerce.service.impl;

import com.qb.ecommerce.config.MapperConfiguration;
import com.qb.ecommerce.dto.UserDTO;
import com.qb.ecommerce.exception.ResourceNotFoundException;
import com.qb.ecommerce.model.User;
import com.qb.ecommerce.repository.UserRepository;
import com.qb.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperConfiguration mapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.getMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> mapper.getMapper().map(user, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> mapper.getMapper().map(user, UserDTO.class));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapper.getMapper().map(userDTO, User.class);
        user = userRepository.save(user);
        return mapper.getMapper().map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        mapper.getMapper().map(userDTO, existingUser);
        existingUser = userRepository.save(existingUser);
        return mapper.getMapper().map(existingUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(existingUser);
    }
}