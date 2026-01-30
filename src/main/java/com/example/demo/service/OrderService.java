package com.example.demo.service;

import com.example.demo.dto.OrderCreateRequest;
import com.example.demo.dto.OrderItemRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.mapper.DTOMapper;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse create(OrderCreateRequest orderCreateRequest) {
        User user = userRepository.findByUsername(orderCreateRequest.username()).orElseThrow(()->new IllegalArgumentException("Username not found"));
        Order order = new Order();
        order.setStatus(Order.Status.NEW);
        user.addOrder(order);

        if(orderCreateRequest.items()==null || orderCreateRequest.items().isEmpty()){
            throw new IllegalArgumentException("Order must have at least one item");
        }
        for(OrderItemRequest orderItemRequest : orderCreateRequest.items()){
            Product p = productRepository.findById(orderItemRequest.productId()).orElseThrow(()->new IllegalArgumentException("Product not found"+ orderItemRequest.productId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(p);
            orderItem.setQty(orderItemRequest.qty());
            orderItem.setPriceAtPurchase(p.getPrice());
            order.addItem(orderItem);
        }
        Order saved = orderRepository.save(order);
        Order loaded = orderRepository.findDetailedById(saved.getId()).orElseThrow(()->new IllegalArgumentException("Order not found"));
        return DTOMapper.toOrderResponse(loaded);
    }

    @Transactional
    public OrderResponse getDetailed(Long id){
        Order loaded = orderRepository.findDetailedById(id).orElseThrow(()->new IllegalArgumentException("Order not found"));
        return DTOMapper.toOrderResponse(loaded);
    }
}
