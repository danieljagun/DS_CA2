package com.example.dsca2.service;

import com.example.dsca2.PredictedEmission;
import com.example.dsca2.repository.PredictedEmissionRepository;

public class EmissionServiceImpl implements EmissionService {

    private final PredictedEmissionRepository predictedEmissionRepository;

    public EmissionServiceImpl(PredictedEmissionRepository predictedEmissionRepository) {
        this.predictedEmissionRepository = predictedEmissionRepository;
    }

    @Override
    public Long createEmission(PredictedEmission predictedEmission) {
        return null;
    }

    @Override
    public PredictedEmission getEmission(String emissionID) {
        return null;
    }

    @Override
    public PredictedEmission updateEmission(PredictedEmission updatedPredictedEmission) {
        return null;
    }

    @Override
    public void deleteEmission(String emissionID) {

    }
}


