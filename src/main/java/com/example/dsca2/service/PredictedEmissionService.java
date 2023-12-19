package com.example.dsca2.service;

import com.example.dsca2.repository.PredictedEmission;

import java.util.List;

public interface PredictedEmissionService {

    Long createPredictedEmission(PredictedEmission predictedEmission);

    PredictedEmission getPredictedEmission(Long emissionID);

    PredictedEmission updatePredictedEmission(Long emissionID, PredictedEmission updatedPredicted);

    void deletePredictedEmission(Long emissionID);

    List<PredictedEmission> getAllPredictedEmissions();

    List<PredictedEmission> getPredictedEmissionsByCategory(String category);
}
