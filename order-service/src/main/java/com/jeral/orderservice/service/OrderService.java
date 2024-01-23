package com.jeral.orderservice.service;

import com.jeral.orderservice.dto.request.OrderRequestDTO;
import com.jeral.orderservice.model.Order;
import com.jeral.orderservice.model.OrderLineItems;
import com.jeral.orderservice.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private final OrderRepo orderRepo;

    @Autowired
    private final WebClient webClient;

    public OrderService(OrderRepo orderRepo, WebClient webClient) {
        this.orderRepo = orderRepo;
        this.webClient = webClient;
    }

    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDTO.getOrderLineItems()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItems(orderLineItems);

        //////////////////////////////////////////////////////////////////
        //call the inventory service and place order if product is existed
        //////////////////////////////////////////////////////////////////
        Boolean response = webClient.get()
                .uri("http://localhost:8082/api/inventory/item_1")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (response) {
            orderRepo.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in stock, Please try again later");
        }
    }

    private OrderLineItems mapToDTO(OrderLineItems orderLineItemsRequestDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsRequestDTO.getPrice());
        orderLineItems.setQty(orderLineItemsRequestDTO.getQty());
        orderLineItems.setSkuCode(orderLineItemsRequestDTO.getSkuCode());
        return orderLineItems;
    }
}
