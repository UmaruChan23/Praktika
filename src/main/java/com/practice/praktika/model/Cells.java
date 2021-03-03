package com.practice.praktika.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Cells {

    @JsonProperty("ForecastDate")
    private String forecastDate;

    @JsonProperty("MinimumTemperature")
    private int minimumTemperature;

    @JsonProperty("MaximumTemperature")
    private int maximumTemperature;

    @JsonProperty("PrecipitationType")
    private String notes;

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public int getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(int minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public int getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(int maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
