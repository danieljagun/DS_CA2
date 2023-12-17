package com.example.dsca2.service;

import com.example.dsca2.repository.ActualEmission;

public interface ActualEmissionService {

    Long createActualEmission(ActualEmission actualEmission);

    ActualEmission getActualEmission(Long emissionID);

    ActualEmission updateActualEmission(Long emissionID, ActualEmission updatedActual);

    void deleteActualEmission(Long emissionID);
}

