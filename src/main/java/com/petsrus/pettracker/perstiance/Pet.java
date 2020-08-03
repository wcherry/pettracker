package com.petsrus.pettracker.perstiance;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Immutable
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long ownerId;
    private String petName;
    private String collarId;

    @Column(name = "created_at")
    private Timestamp when;
    @Column(name = "tracking_zone")
    private Integer zone;


    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getPetName() {
        return petName;
    }

    public String getCollarId() {
        return collarId;
    }

    public Timestamp getWhen() {
        return when;
    }

    public Integer getZone() {
        return zone;
    }
}
