package com.project.boxinator.controllers;


import com.project.boxinator.models.Country;
import com.project.boxinator.models.Shipment;
import com.project.boxinator.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public void addCountry (@RequestBody Country country) { countryService.addCountry(country); }

    @GetMapping
    public ResponseEntity getAllCountries() { return ResponseEntity.ok(countryService.getAllCountries()); }

    @GetMapping("{countryId}")
    public ResponseEntity getCountry(@PathVariable Integer countryId) {
        return ResponseEntity.ok(countryService.getCountryById(countryId));
    }

    @PutMapping("{countryId}")
    public void updateCountry(@PathVariable Integer countryId, @RequestBody Country country) {
        if(countryId != country.getId())
            ResponseEntity.badRequest().build();
        countryService.update(country);
    }





}
