package com.example.dsca2;

public class PredictedEmission {

    private Long emissionID;
    private String category;
    private int year;
    private String scenario;
    private String gasUnits;
    private String value;

    public PredictedEmission(Long emissionID, String category, int year, String scenario, String gasUnits, String value) {
        this.emissionID = emissionID;
        this.category = category;
        this.year = year;
        this.scenario = scenario;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Emission{" +
                "emissionID='" + emissionID + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                ", scenario='" + scenario + '\'' +
                ", gasUnits='" + gasUnits + '\'' +
                ", value=" + value +
                '}';
    }
}
