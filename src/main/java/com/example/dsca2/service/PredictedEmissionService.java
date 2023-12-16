package com.example.dsca2.service;

import com.example.dsca2.repository.PredictedEmission;

public interface PredictedEmissionService {

    Long createEmission(PredictedEmission predictedEmission);

    PredictedEmission getEmission(Long emissionID);

    PredictedEmission updateEmission(PredictedEmission updatedPredicted);

    void deleteEmission(Long emissionID);
}
