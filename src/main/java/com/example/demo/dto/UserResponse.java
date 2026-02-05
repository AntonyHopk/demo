package com.example.demo.dto;

import java.util.Set;

public record UserResponse(Long id, String username, ProfileResponse profile, Set<String> roles, AddressDTO address) {
}
