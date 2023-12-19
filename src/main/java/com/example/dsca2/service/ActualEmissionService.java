package com.example.dsca2.service;

import com.example.dsca2.repository.ActualEmission;

import java.util.List;

public interface ActualEmissionService {

    Long createActualEmission(ActualEmission actualEmission);

    ActualEmission getActualEmission(Long emissionID);

    ActualEmission updateActualEmission(Long emissionID, ActualEmission updatedActual);

    void deleteActualEmission(Long emissionID);

    List<ActualEmission> getAllActualEmissions();

    List<ActualEmission> getActualEmissionsByCategory(String category);
}

