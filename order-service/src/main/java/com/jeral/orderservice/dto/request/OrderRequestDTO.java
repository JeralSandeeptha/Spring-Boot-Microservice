package com.jeral.orderservice.dto.request;

import com.jeral.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private List<OrderLineItems> orderLineItems;
}
