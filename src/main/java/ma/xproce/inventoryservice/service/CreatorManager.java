package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Creator; // Adjust the import based on your package structure
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository; // Adjust the import based on your package structure
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreatorManager implements CreatorService {

    private final CreatorRepository creatorRepository;

    @Autowired
    public CreatorManager(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Override
    public Creator save(Creator creator) {
        return creatorRepository.save(creator);
    }

    @Override
    public Optional<Creator> findById(Long id) {
        return creatorRepository.findById(id);
    }

    @Override
    public List<Creator> findAll() {
        return creatorRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        creatorRepository.deleteById(id);
    }
}