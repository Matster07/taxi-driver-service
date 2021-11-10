package com.matster.taxi.order.system.driverservice.service;

import com.matster.taxi.order.system.driverservice.exception.OrderStatusException;
import com.matster.taxi.order.system.driverservice.exception.OrdersNotFoundException;
import com.matster.taxi.order.system.driverservice.model.OrderResponse;
import com.matster.taxi.order.system.driverservice.model.UpdateOrderRequest;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    @Value(value = "${app.integration.order-service-address}")
    private String orderServiceAddress;

    private final RestTemplate restTemplate;

    public List<OrderResponse> getOrders() {
        ResponseEntity<OrderResponse[]> response = restTemplate
                .getForEntity(
                            "http://" +
                                orderServiceAddress +
                                "/api/order/v1/orders",
                        OrderResponse[].class);

        Optional<OrderResponse[]> orders = Optional.ofNullable(response.getBody());

        return Arrays.asList(orders.orElseThrow(() -> new OrdersNotFoundException("An error occurred during getting orders")));
    }

    public OrderResponse updateOrder(Long id, UpdateOrderRequest updateOrderReq) {
        ResponseEntity<OrderResponse> response = restTemplate
                .postForEntity(
                            "http://" +
                                orderServiceAddress +
                                "/api/order/v1/orders/" +
                                id +
                                "/status",
                        updateOrderReq, OrderResponse.class);

        Optional<OrderResponse> body = Optional.ofNullable(response.getBody());

        return body.orElseThrow(() -> new OrderStatusException("An error occurred during updating order status"));
    }
}
