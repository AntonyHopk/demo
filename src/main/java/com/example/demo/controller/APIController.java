package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class APIController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;


    @PostMapping("/users")
    public UserResponse createUser(@RequestBody UserCreateRequest req) {
        return userService.create(req);
    }

    @GetMapping("/users/by-username")
    public UserResponse getUser(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest req) {
        return productService.createProduct(req);
    }

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest req) {
        return orderService.create(req);
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return orderService.getDetailed(id);
    }


}
