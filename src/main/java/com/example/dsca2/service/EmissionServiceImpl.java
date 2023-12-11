package com.example.dsca2.service;

import com.example.dsca2.Emission;
import com.example.dsca2.repository.EmissionEntity;
import com.example.dsca2.repository.EmissionRepository;

public class EmissionServiceImpl implements EmissionService {

    private final EmissionRepository emissionRepository;

    public EmissionServiceImpl(EmissionRepository emissionRepository) {
        this.emissionRepository = emissionRepository;
    }

    @Override
    public Long createEmission(Emission emission) {
        return null;
    }

    @Override
    public Emission getEmission(String emissionID) {
        return null;
    }

    @Override
    public Emission updateEmission(Emission updatedEmission) {
        return null;
    }

    @Override
    public void deleteEmission(String emissionID) {

    }
}


