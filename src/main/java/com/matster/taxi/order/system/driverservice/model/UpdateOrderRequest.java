package com.matster.taxi.order.system.driverservice.model;

import com.matster.taxi.order.system.driverservice.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderRequest {

    private Integer driverId;
    private OrderStatus status;
}
