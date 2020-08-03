package com.petsrus.pettracker.perstiance;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Immutable
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String contactPhone;
    private String username;
    private boolean premiumAccount;
    private Double longitude;
    private Double latitude;
    private Double elevation;
    @Column(name = "created_at")
    private Timestamp when;
    @Column(name = "tracking_zone")
    private Integer zone;


    public String getFullName(){
        return String.format("%s %s", firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getUsername() {
        return username;
    }

    public boolean isPremiumAccount() {
        return premiumAccount;
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

    public Timestamp getWhen() {
        return when;
    }

    public Integer getZone() {
        return zone;
    }
}
