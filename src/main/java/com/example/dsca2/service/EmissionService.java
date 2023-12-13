package com.example.dsca2.service;

import com.example.dsca2.PredictedEmission;

public interface EmissionService {

    Long createEmission(PredictedEmission predictedEmission);

    PredictedEmission getEmission(String emissionID);

    PredictedEmission updateEmission(PredictedEmission updatedPredictedEmission);

    void deleteEmission(String emissionID);
}

