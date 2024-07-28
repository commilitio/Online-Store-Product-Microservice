package com.commilitio.product_service.repository;

import com.commilitio.product_service.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    boolean existsByProcessorAndRamAndGraphicsCardAndOperatingSystemAndDiscCapacity(
            String processor, String ram, String graphicsCard, String operatingSystem, String discCapacity);
}
