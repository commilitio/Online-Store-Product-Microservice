package com.commilitio.product_service.controller;

import com.commilitio.product_service.model.Smartphone;
import com.commilitio.product_service.model.SmartphoneDto;
import com.commilitio.product_service.service.SmartphoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {

    private final SmartphoneService smartphoneService;

    @GetMapping
    public List<SmartphoneDto> getSmartphones(Pageable pageable) {
        log.info("getSmartphones method called with data: {}", pageable);
        return smartphoneService.getSmartphones(pageable);
    }

    @PostMapping
    public SmartphoneDto addSmartphone(@RequestBody Smartphone smartphone) {
        log.info("adding new Smartphone with data: {}", smartphone);
        return smartphoneService.addSmartphone(smartphone);
    }

    @PutMapping("/{id}")
    public SmartphoneDto updateSmartphone(@PathVariable Long id, @RequestBody Smartphone smartphone) {
        log.info("Updating smartphone with id: {}, and data: {}", id, smartphone);
        return smartphoneService.updateSmartphone(id, smartphone);
    }

    @DeleteMapping("/{id}")
    public void deleteSmartphone(@PathVariable Long id) {
        log.info("Deleting smartphone with id: {}", id);
        smartphoneService.deleteSmartphone(id);
    }
}
