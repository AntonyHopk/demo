package com.example.demo.dto;

import java.time.OffsetDateTime;

public record CommentResponse(Long id, UserShortResponse user, String text, OffsetDateTime createdAt) {
}
