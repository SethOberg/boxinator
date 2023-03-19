package com.project.boxinator.controllers;


import com.project.boxinator.models.Country;
import com.project.boxinator.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public void addCountry (@RequestBody Country country) { countryService.addCountry(country); }

    @GetMapping
    public ResponseEntity getAllCountries() { return ResponseEntity.ok(countryService.getAllCountries()); }





}
