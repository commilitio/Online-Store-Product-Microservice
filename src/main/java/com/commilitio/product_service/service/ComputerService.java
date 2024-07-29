package com.commilitio.product_service.service;

import com.commilitio.product_service.mapper.ComputerMapper;
import com.commilitio.product_service.model.Computer;
import com.commilitio.product_service.model.ComputerDto;
import com.commilitio.product_service.repository.ComputerRepository;
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
public class ComputerService {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;

    public List<ComputerDto> getComputers(Pageable pageable) {
        Page<Computer> computers = computerRepository.findAll(pageable);
        return computerMapper.toDtos(computers.toList());
    }

    @Transactional
    public ComputerDto addComputer(Computer computer) {
        boolean computerExists = computerRepository.checkIfComputerExists(
                computer.getProcessor(),
                computer.getRam(),
                computer.getGraphicsCard(),
                computer.getOperatingSystem(),
                computer.getDiscCapacity()
        );
        if (computerExists) {
            log.info("Computer with specified configuration already exists");
            throw new IllegalArgumentException("Computer with the specified configuration already exists");
        }
        Computer savedComputer = computerRepository.save(computer);
        log.info("Added new computer with id: {}", savedComputer.getId());
        return computerMapper.toDto(savedComputer);
    }

    @Transactional
    public ComputerDto updateComputer(Long id, Computer computer) {
        Computer computerToUpdate = computerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        computerToUpdate.setProcessor(computer.getProcessor());
        computerToUpdate.setRam(computer.getRam());
        computerToUpdate.setGraphicsCard(computer.getGraphicsCard());
        computerToUpdate.setOperatingSystem(computer.getOperatingSystem());
        computerToUpdate.setDiscCapacity(computer.getDiscCapacity());
        computerRepository.save(computerToUpdate);
        log.info("Updated computer with id {} with new data: {}", id, computerToUpdate);
        return computerMapper.toDto(computerToUpdate);
    }

    public void deleteComputer(Long id) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Computer not found"));
        computerRepository.delete(computer);
        log.info("Deleted computer with id: {}", id);
    }
}
