package com.volvo.vcc.controller;

import com.volvo.vcc.exception.CongestionTaxException;
import com.volvo.vcc.services.CongestionTaxCalculator;
import com.volvo.vcc.util.TollFreeHelper;
import com.volvo.vcc.vo.CongestionTaxCalRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/congestionTaxCalculatorService")
public class CongestionTaxCalculatorController {

    @Autowired
    private CongestionTaxCalculator congestionTaxCalculator;

    @Autowired
    private TollFreeHelper tollFreeHelper;

    @PostMapping
    public ResponseEntity<Integer> evaluateCongestionTax(@RequestBody CongestionTaxCalRequestMapper requestMapper) throws CongestionTaxException {
        if (requestMapper.getVehicle() == null || tollFreeHelper.isTollFreeVehicle(requestMapper.getVehicle())) {
            return ResponseEntity.ok(0);
        }

        int totalTax = congestionTaxCalculator.getTax(requestMapper.getTimeList());
        return ResponseEntity.ok(totalTax);
    }
}
