package com.example.demo.dto;

import java.math.BigDecimal;

public record ProductCreateRequest(String title, BigDecimal price) {
}
