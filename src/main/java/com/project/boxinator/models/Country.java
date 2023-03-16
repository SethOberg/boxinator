package com.project.boxinator.models;

import com.project.boxinator.enums.WeightOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(length = 150, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private Double countryMultiplier;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCountryMultiplier() {
        return countryMultiplier;
    }

    public void setCountryMultiplier(Double countryMultiplier) {
        this.countryMultiplier = countryMultiplier;
    }
}