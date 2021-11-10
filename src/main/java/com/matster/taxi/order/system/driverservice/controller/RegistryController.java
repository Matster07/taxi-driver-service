package com.matster.taxi.order.system.driverservice.controller;

import com.matster.taxi.order.system.driverservice.entity.Driver;
import com.matster.taxi.order.system.driverservice.model.RegisterDriverRequest;
import com.matster.taxi.order.system.driverservice.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Описание API для управления водителями")
@RestController
@RequestMapping("/registry")
@AllArgsConstructor
public class RegistryController {

    private final DriverService driverService;

    @PostMapping
    @ApiOperation("Регистрация водителя")
    public ResponseEntity<Driver> createCustomer(@RequestBody RegisterDriverRequest driverReq) {
        return ResponseEntity.ok().body(driverService.create(driverReq));
    }
}
