package com.matster.taxi.order.system.driverservice.controller;

import com.matster.taxi.order.system.driverservice.entity.Driver;
import com.matster.taxi.order.system.driverservice.enums.OrderStatus;
import com.matster.taxi.order.system.driverservice.model.OrderResponse;
import com.matster.taxi.order.system.driverservice.model.UpdateOrderRequest;
import com.matster.taxi.order.system.driverservice.service.DriverService;
import com.matster.taxi.order.system.driverservice.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Описание API для отображения и управления заказами")
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final DriverService driverService;

    @GetMapping
    @ApiOperation(value = "Получение всех свободных заказов")
    public ResponseEntity<List<OrderResponse>> getUnassignedOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @PostMapping("/{id}/status")
    @ApiOperation(value = "Обновление статуса заказа")
    public ResponseEntity<OrderResponse> updateOrder(@ApiParam(value = "Идентификатор заказа")
                            @PathVariable Long id,
                            @RequestBody UpdateOrderRequest updateOrderRequest) {
        driverService.checkIfExist(updateOrderRequest.getDriverId());

        return ResponseEntity.ok(orderService.updateOrder(id, updateOrderRequest));
    }

}
