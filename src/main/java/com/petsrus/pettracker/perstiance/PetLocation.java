package com.petsrus.pettracker.perstiance;

import com.petsrus.pettracker.Constants;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Immutable
@Table(name = "locations")
public class PetLocation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long ownerId;
    private Long petId;
    private Double longitude;
    private Double latitude;
    private Double elevation;
    @Column(name = "created_at")
    private Timestamp when;
    @Column(name = "tracking_zone")
    private Integer zone;

    public PetLocation(Long ownerId, Long petId, Double longitude, Double latitude, Double elevation, Long whenInMillis, Integer zone) {
        this.ownerId = ownerId;
        this.petId = petId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.when = new Timestamp(whenInMillis);
        this.zone = zone;
    }

    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getPetId() {
        return petId;
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
        return when != null ? when.getTime() : 0L;
    }

    public Integer getZone() {
        return zone;
    }
}
