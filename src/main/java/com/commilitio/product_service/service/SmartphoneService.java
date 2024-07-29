package com.commilitio.product_service.service;

import com.commilitio.product_service.mapper.SmartphoneMapper;
import com.commilitio.product_service.model.Smartphone;
import com.commilitio.product_service.model.SmartphoneDto;
import com.commilitio.product_service.repository.SmartphoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final SmartphoneMapper smartphoneMapper;

    public List<SmartphoneDto> getSmartphones(Pageable pageable) {
        Page<Smartphone> smartphones = smartphoneRepository.findAll(pageable);
        return smartphoneMapper.toDtos(smartphones.toList());
    }

    @Transactional
    public SmartphoneDto addSmartphone(Smartphone smartphone) {
        boolean smartphoneExists = smartphoneRepository.checkIfSmartphoneExists(
                smartphone.getColor(),
                smartphone.getBatteryCapacity(),
                smartphone.getBrand(),
                smartphone.getScreenSize()
        );
        if (smartphoneExists) {
            log.info("Smartphone with specified configuration already exists");
            throw new IllegalArgumentException("Smartphone with the specified configuration already exists");
        }
        Smartphone savedSmartphone = smartphoneRepository.save(smartphone);
        log.info("Added new smartphone with id: {}", savedSmartphone.getId());
        return smartphoneMapper.toDto(savedSmartphone);
    }

    @Transactional
    public SmartphoneDto updateSmartphone(Long id, Smartphone smartphone) {
        Smartphone smartphoneToUpdate = smartphoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Smartphone not found"));
        smartphoneToUpdate.setColor(smartphone.getColor());
        smartphoneToUpdate.setBatteryCapacity(smartphone.getBatteryCapacity());
        smartphoneToUpdate.setBrand(smartphone.getBrand());
        smartphoneToUpdate.setScreenSize(smartphone.getScreenSize());
        smartphoneRepository.save(smartphoneToUpdate);
        log.info("Updated smartphone with id {} with new data: {}", id, smartphoneToUpdate);
        return smartphoneMapper.toDto(smartphoneToUpdate);
    }

    public void deleteSmartphone(Long id) {
        Smartphone smartphone = smartphoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Smartphone not found"));
        smartphoneRepository.delete(smartphone);
        log.info("Deleted smartphone with id: {}", id);
    }
}
