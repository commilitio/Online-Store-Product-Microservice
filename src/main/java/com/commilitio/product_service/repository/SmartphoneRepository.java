package com.commilitio.product_service.repository;

import com.commilitio.product_service.model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {

    boolean checkIfSmartphoneExists(String color, String batteryCapacity, String brand, String screenSize);
}
