package com.project.boxinator.services;

import com.project.boxinator.models.Country;
import com.project.boxinator.exceptions.CountryNotFoundException;
import com.project.boxinator.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() { return countryRepository.findAll(); }


    public Country getCountryById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    public Country getCountryByName(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new CountryNotFoundException("Country with name " + name + " not found"));
    }

    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    public Country update(Country country) {
        getCountryById(country.getId());
        return countryRepository.save(country);
    }



}
