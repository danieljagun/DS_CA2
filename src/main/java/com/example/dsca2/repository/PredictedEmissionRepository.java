package com.example.dsca2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictedEmissionRepository extends JpaRepository<PredictedEmission, Long> {

    List<PredictedEmission> findByCategory(String category);

}
