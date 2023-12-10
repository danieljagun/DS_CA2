package com.example.dsca2.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmissionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String emissionID;

    private String category;
    private int year;
    private String scenario;
    private String gasUnits;
    private double value;

    public EmissionEntity() {
    }

    public EmissionEntity(String emissionID, String category, int year, String scenario, String gasUnits, double value) {
        this.emissionID = emissionID;
        this.category = category;
        this.year = year;
        this.scenario = scenario;
        this.gasUnits = gasUnits;
        this.value = value;
    }

    public String getEmissionID() {
        return emissionID;
    }

    public void setEmissionID(String emissionID) {
        this.emissionID = emissionID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getGasUnits() {
        return gasUnits;
    }

    public void setGasUnits(String gasUnits) {
        this.gasUnits = gasUnits;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EmissionEntity{" +
                "emissionID='" + emissionID + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                ", scenario='" + scenario + '\'' +
                ", gasUnits='" + gasUnits + '\'' +
                ", value=" + value +
                '}';
    }
}
