package com.example.dsca2.service;

import com.example.dsca2.repository.ActualEmission;
import com.example.dsca2.repository.PredictedEmissionRepository;

public class ActualEmissionServiceImpl implements ActualEmissionService {

    private final PredictedEmissionRepository predictedEmissionRepository;

    public ActualEmissionServiceImpl(PredictedEmissionRepository predictedEmissionRepository) {
        this.predictedEmissionRepository = predictedEmissionRepository;
    }


    @Override
    public Long createEmission(ActualEmission actualEmission) {
        return null;
    }

    @Override
    public ActualEmission getEmission(Long emissionID) {
        return null;
    }

    @Override
    public ActualEmission updateEmission(ActualEmission updatedActual) {
        return null;
    }

    @Override
    public void deleteEmission(Long emissionID) {

    }
}


