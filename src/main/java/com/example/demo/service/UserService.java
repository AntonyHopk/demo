package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public User create(UserCreateRequest user) {
        return userRepository.save(new User(user.getUsername()));
    }
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }
    @Transactional
    public User update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        user.setUsername(userUpdateRequest.getUsername());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Long id) {
            super("User with id " + id + " not found");
        }
    }
}
