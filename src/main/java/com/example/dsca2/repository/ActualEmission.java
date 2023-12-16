package com.example.dsca2.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "actualEmissions")
public class ActualEmission {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long emissionID;

    private String category;
    private String gasUnits;
    private String value;

    public ActualEmission() {

    }

    public ActualEmission(Long emissionID, String category, String gasUnits, String value) {
        this.emissionID = emissionID;
        this.category = category;
        this.gasUnits = gasUnits;
        this.value = value;
    }

    public Long getEmissionID() {
        return emissionID;
    }

    public void setEmissionID(Long emissionID) {
        this.emissionID = emissionID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGasUnits() {
        return gasUnits;
    }

    public void setGasUnits(String gasUnits) {
        this.gasUnits = gasUnits;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ActualEmission{" +
                "emissionID=" + emissionID +
                ", category='" + category + '\'' +
                ", gasUnits='" + gasUnits + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}


