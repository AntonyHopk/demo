package com.example.demo.dto;

import java.math.BigDecimal;

public record OrderItemResponse(Long id, int qty, BigDecimal price,ProductResponse product) {
}
