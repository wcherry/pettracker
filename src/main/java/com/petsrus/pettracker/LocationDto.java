package com.petsrus.pettracker;

import org.hibernate.annotations.Immutable;


@Immutable
public class LocationDto {
    private String collarId;
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private Long when;

    public LocationDto(String collarId, Double longitude, Double latitude, Double elevation, Long when) {
        this.collarId = collarId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.when = when;
    }

    public String getCollarId() {
        return collarId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public Long getWhen() {
        return when;
    }
}
