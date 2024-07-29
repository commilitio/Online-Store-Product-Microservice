package com.commilitio.product_service.controller;

import com.commilitio.product_service.model.Computer;
import com.commilitio.product_service.model.ComputerDto;
import com.commilitio.product_service.service.ComputerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping
    public List<ComputerDto> getComputers(Pageable pageable) {
        log.info("getComputers method called with data: {}", pageable);
        return computerService.getComputers(pageable);
    }

    @PostMapping
    public ComputerDto addComputer(@RequestBody Computer computer) {
        log.info("adding new Computer with data: {}", computer);
        return computerService.addComputer(computer);
    }

    @PutMapping("/{id}")
    public ComputerDto updateComputer(@PathVariable Long id, @RequestBody Computer computer) {
        log.info("Updating computer with id: {}, and data: {}", id, computer);
        return computerService.updateComputer(id, computer);
    }

    @DeleteMapping("/{id}")
    public void deleteComputer(@PathVariable Long id) {
        log.info("Deleting computer with id: {}", id);
        computerService.deleteComputer(id);
    }
}
