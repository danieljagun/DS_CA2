package com.example.dsca2.service;

import com.example.dsca2.repository.ActualEmission;
import com.example.dsca2.repository.ActualEmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActualEmissionServiceImpl implements ActualEmissionService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ActualEmissionRepository actualEmissionRepository;

    public ActualEmissionServiceImpl(ActualEmissionRepository actualEmissionRepository) {
        this.actualEmissionRepository = actualEmissionRepository;
    }

    @Override
    public Long createActualEmission(ActualEmission actualEmission) {
        ActualEmission newActualEmission = actualEmissionRepository.save(actualEmission);
        logger.info("Created new emission with ID: {}", newActualEmission.getEmissionID());
        return newActualEmission.getEmissionID();
    }

    @Override
    public ActualEmission getActualEmission(Long emissionID) {
        Optional<ActualEmission> actualEmissionOptional = actualEmissionRepository.findById(emissionID);
        if (actualEmissionOptional.isPresent()) {
            ActualEmission actualEmission = actualEmissionOptional.get();
            logger.info("Retrieved emission with ID: {}", actualEmission.getEmissionID());
            return actualEmission;
        } else {
            logger.info("Emission with ID {} not found", emissionID);
            return null;
        }
    }

    @Override
    public ActualEmission updateActualEmission(Long emissionID, ActualEmission updatedActual) {
        Optional<ActualEmission> actualEmissionOptional = actualEmissionRepository.findById(emissionID);
        if (actualEmissionOptional.isPresent()) {
            ActualEmission actualEmission = actualEmissionOptional.get();
            actualEmission.setCategory(updatedActual.getCategory());
            actualEmission.setGasUnits(updatedActual.getGasUnits());
            actualEmission.setValue(updatedActual.getValue());

            ActualEmission savedActualEmission = actualEmissionRepository.save(actualEmission);
            logger.info("Updated emission with ID: {}", savedActualEmission.getEmissionID());
            return savedActualEmission;
        } else {
            logger.info("Emission with ID {} not found", emissionID);
            return null;
        }
    }

    @Override
    public void deleteActualEmission(Long emissionID) {
        Optional<ActualEmission> actualEmissionOptional = actualEmissionRepository.findById(emissionID);
        if (actualEmissionOptional.isPresent()) {
            actualEmissionRepository.deleteById(emissionID);
            logger.info("Deleted emission with ID: {}", emissionID);
        } else {
            logger.info("Emission with ID {} not found, deletion failed.", emissionID);
        }
    }

    @Override
    public List<ActualEmission> getAllActualEmissions() {
        List<ActualEmission> allActualEmissions = actualEmissionRepository.findAll();
        if (!allActualEmissions.isEmpty()) {
            logger.info("Retrieved {} actual emissions", allActualEmissions.size());
        } else {
            logger.info("No actual emissions found");
        }
        return allActualEmissions;
    }

    @Override
    public List<ActualEmission> getActualEmissionsByCategory(String category) {
        List<ActualEmission> emissionsByCategory = actualEmissionRepository.findByCategory(category);
        if (!emissionsByCategory.isEmpty()) {
            logger.info("Retrieved {} actual emissions for category: {}", emissionsByCategory.size(), category);
        } else {
            logger.info("No actual emissions found for category: {}", category);
        }
        return emissionsByCategory;
    }
}


