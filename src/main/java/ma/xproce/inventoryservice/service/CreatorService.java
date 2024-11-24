package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Creator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CreatorService {
    Creator save(Creator creator); // Method to save a creator

    Optional<Creator> findById(Long id); // Method to find a creator by ID

    List<Creator> findAll(); // Method to get all creators

    void deleteById(Long id); // Method to delete a creator by ID

}
