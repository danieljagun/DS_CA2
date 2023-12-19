package com.example.dsca2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActualEmissionRepository extends JpaRepository<ActualEmission, Long> {

    List<ActualEmission> findByCategory(String category);
}
