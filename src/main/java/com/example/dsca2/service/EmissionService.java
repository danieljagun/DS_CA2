package com.example.dsca2.service;

import com.example.dsca2.Emission;

public interface EmissionService {

    Long createEmission(Emission emission);

    Emission getEmission(String emissionID);

    Emission updateEmission(Emission updatedEmission);

    void deleteEmission(String emissionID);
}

