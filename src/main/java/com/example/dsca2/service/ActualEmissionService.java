package com.example.dsca2.service;

import com.example.dsca2.repository.ActualEmission;

public interface ActualEmissionService {

    Long createEmission(ActualEmission actualEmission);

    ActualEmission getEmission(Long emissionID);

    ActualEmission updateEmission(ActualEmission updatedActual);

    void deleteEmission(Long emissionID);
}

