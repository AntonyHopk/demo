package com.example.demo.dto;

import java.util.List;

public record OrderCreateRequest(String username,List<OrderItemRequest> items) {
}
