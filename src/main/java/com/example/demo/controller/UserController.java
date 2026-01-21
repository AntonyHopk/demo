package com.example.demo.controller;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Tag(name="Users",description = "sadasds")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findById(id);
    }
    @Operation(
            summary = "Создать пользователя",
            description = "Создает нового пользователя на основе JSON-данных",
            tags = {"Users"},
            operationId = "createUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description="sadasdas", required = true),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Пользователь успешно создан"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные")
            }
    )

    @PostMapping
    public User save(@RequestBody UserCreateRequest user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserUpdateRequest user) {
        return userService.update(id, user);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
