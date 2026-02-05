package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.mapper.DTOMapper;
import com.example.demo.model.Address;
import com.example.demo.model.Profile;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserResponse create(UserCreateRequest req) {
        User user = new User(req.username());

        if (req.profile() != null) {
            Profile profile = new Profile();
            profile.setFirstName(req.profile().firstName());
            profile.setLastName(req.profile().lastName());
            user.setProfile(profile);
        }
        if (req.roles() != null) {
            for (String roleName : req.roles()) {
                Role role = roleRepository.findByName(roleName).orElseGet(() -> roleRepository.save(new Role(roleName)));
                user.addRole(role);
            }
        }
       if (req.address() != null) {
           Address address = new Address();
           address.setCity(req.address().city());
           address.setCountry(req.address().country());
           address.setStreet(req.address().street());
           user.setAddress(address);
       }
        User savedUser = userRepository.save(user);
        User loadUser = userRepository.findWithProfileAndRoleByUsername(savedUser.getUsername()).orElseThrow();
        return DTOMapper.toUserResponse(loadUser);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(userUpdateRequest.getUsername());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse getByUsername(String username) {
        User user = userRepository.findWithProfileAndRoleByUsername(username).orElseThrow(() -> new IllegalArgumentException("Username not found!"));
        return DTOMapper.toUserResponse(user);
    }


    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Long id) {
            super("User with id " + id + " not found");
        }
    }
}
