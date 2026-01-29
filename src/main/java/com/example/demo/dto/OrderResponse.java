package com.example.demo.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record OrderResponse(Long id, String status, OffsetDateTime createdAt, UserShortResponse user, List<OrderItemResponse> items) {
}
