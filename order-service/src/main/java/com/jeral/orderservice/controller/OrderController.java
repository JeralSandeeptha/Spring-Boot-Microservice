package com.jeral.orderservice.controller;

import com.jeral.orderservice.dto.request.OrderRequestDTO;
import com.jeral.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public String placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        orderService.placeOrder(orderRequestDTO);
        return "Order place successfully";
    }
}
