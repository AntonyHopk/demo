package com.example.demo.dto;

import java.util.List;

public record GroupResponse(Long id, String name, List<UserShortResponse> users) {
}
