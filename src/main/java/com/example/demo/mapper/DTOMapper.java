package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.model.*;

import java.util.stream.Collectors;

public final class DTOMapper {

    private DTOMapper() {
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(),
                user.getUsername(),
                user.getProfile() == null ? null : new ProfileResponse(user.getProfile().getFirstName(),
                        user.getProfile().getLastName()),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                user.getAddress() == null ? null : new AddressDTO(user.getAddress().getCountry(),
                        user.getAddress().getCity(),user.getAddress().getStreet()));
    }

    public static UserShortResponse toUserShortResponse(User user) {
        return new UserShortResponse(user.getId(),
                user.getUsername());
    }

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(),
                product.getTitle(),
                product.getPrice());
    }

    public static OrderResponse toOrderResponse(Order order) {
        var items = order.getItems().stream()
                .map(DTOMapper::toOrderItemResponse)
                .toList();

        return new OrderResponse(order.getId(),
                order.getStatus().name(),
                order.getCreatedAt(),
                toUserShortResponse(order.getUser()), items);
    }

    private static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(orderItem.getId(),
                orderItem.getQty(),
                orderItem.getPriceAtPurchase(),
                toProductResponse(orderItem.getProduct()));
    }
}
