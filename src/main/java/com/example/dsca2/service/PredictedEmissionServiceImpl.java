package com.example.dsca2.service;

import com.example.dsca2.repository.PredictedEmission;
import com.example.dsca2.repository.PredictedEmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredictedEmissionServiceImpl implements PredictedEmissionService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PredictedEmissionRepository predictedEmissionRepository;

    public PredictedEmissionServiceImpl(PredictedEmissionRepository predictedEmissionRepository) {
        this.predictedEmissionRepository = predictedEmissionRepository;
    }

    @Override
    public Long createPredictedEmission(PredictedEmission predictedEmission) {
        PredictedEmission newPredictedEmission = predictedEmissionRepository.save(predictedEmission);
        logger.info("Created new predicted emission with ID: {}", newPredictedEmission.getEmissionID());
        return newPredictedEmission.getEmissionID();
    }

    @Override
    public PredictedEmission getPredictedEmission(Long emissionID) {
        Optional<PredictedEmission> predictedEmissionOptional = predictedEmissionRepository.findById(emissionID);
        if (predictedEmissionOptional.isPresent()) {
            PredictedEmission predictedEmission = predictedEmissionOptional.get();
            logger.info("Retrieved predicted emission with ID: {}", predictedEmission.getEmissionID());
            return predictedEmission;
        } else {
            logger.info("Predicted emission with ID {} not found", emissionID);
            return null;
        }
    }

    @Override
    public PredictedEmission updatePredictedEmission(Long emissionID, PredictedEmission updatedPredicted) {
        Optional<PredictedEmission> predictedEmissionOptional = predictedEmissionRepository.findById(emissionID);
        if (predictedEmissionOptional.isPresent()) {
            PredictedEmission predictedEmission = predictedEmissionOptional.get();
            predictedEmission.setCategory(updatedPredicted.getCategory());
            predictedEmission.setYear(updatedPredicted.getYear());
            predictedEmission.setScenario(updatedPredicted.getScenario());
            predictedEmission.setGasUnits(updatedPredicted.getGasUnits());
            predictedEmission.setValue(updatedPredicted.getValue());

            PredictedEmission savedPredictedEmission = predictedEmissionRepository.save(predictedEmission);
            logger.info("Updated predicted emission with ID: {}", savedPredictedEmission.getEmissionID());
            return savedPredictedEmission;
        } else {
            logger.info("Predicted emission with ID {} not found", emissionID);
            return null;
        }
    }

    @Override
    public void deletePredictedEmission(Long emissionID) {
        Optional<PredictedEmission> predictedEmissionOptional = predictedEmissionRepository.findById(emissionID);
        if (predictedEmissionOptional.isPresent()) {
            predictedEmissionRepository.deleteById(emissionID);
            logger.info("Deleted predicted emission with ID: {}", emissionID);
        } else {
            logger.info("Predicted emission with ID {} not found, deletion failed.", emissionID);
        }
    }

    @Override
    public List<PredictedEmission> getAllPredictedEmissions() {
        List<PredictedEmission> allPredictedEmissions = predictedEmissionRepository.findAll();
        if (!allPredictedEmissions.isEmpty()) {
            logger.info("Retrieved {} predicted emissions", allPredictedEmissions.size());
        } else {
            logger.info("No predicted emissions found");
        }
        return allPredictedEmissions;
    }

    @Override
    public List<PredictedEmission> getPredictedEmissionsByCategory(String category) {
        List<PredictedEmission> emissionsByCategory = predictedEmissionRepository.findByCategory(category);
        if (!emissionsByCategory.isEmpty()) {
            logger.info("Retrieved {} predicted emissions for category: {}", emissionsByCategory.size(), category);
        } else {
            logger.info("No actual predicted found for category: {}", category);
        }
        return emissionsByCategory;
    }
}
