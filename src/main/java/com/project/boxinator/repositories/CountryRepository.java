package com.project.boxinator.repositories;

import com.project.boxinator.models.Country;
import com.project.boxinator.models.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer>{
}
