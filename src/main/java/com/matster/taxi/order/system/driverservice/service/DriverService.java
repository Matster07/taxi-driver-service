package com.matster.taxi.order.system.driverservice.service;

import com.matster.taxi.order.system.driverservice.entity.Driver;
import com.matster.taxi.order.system.driverservice.exception.DriverNotFoundException;
import com.matster.taxi.order.system.driverservice.model.RegisterDriverRequest;
import com.matster.taxi.order.system.driverservice.repository.DriverRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DriverService {

    private final DriverRepository driverRepository;

    public void checkIfExist(Integer id) {
        Optional<Driver> driver = driverRepository.findDriverById(Integer.valueOf(id));

        if (driver.isEmpty()) throw new DriverNotFoundException("Driver " + id + " wasn't found");
    }

    public Driver create(RegisterDriverRequest driverReq) {
        Driver driver = new Driver();

        driver.setFirstname(driverReq.getFirstname());
        driver.setSecondname(driverReq.getSecondname());

        driverRepository.save(driver);

        log.info("{} was successfully registered", driver);

        return driver;
    }
}
