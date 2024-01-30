package com.volvo.vcc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TollFreeHelper {

    @Value("${vehicle.tollFree}")
    private String tollFreeVehiclesConfig;

    public boolean isTollFreeVehicle(String vehicleType) {
        List<String> tollFreeVehicles = Arrays.asList(tollFreeVehiclesConfig.split(","));
        return tollFreeVehicles.contains(vehicleType);
    }
}
