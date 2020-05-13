package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhisky(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "containing", required = false) String containing,
            @RequestParam(name = "start", required = false) String start

    ){

        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByNameContaining( containing), HttpStatus.OK);
        }
        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByNameStartsWith( start), HttpStatus.OK);
        }
        if (name != null) {
            return new ResponseEntity<>(whiskyRepository.findByName(name), HttpStatus.OK);
        }
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if (age != null) {
            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
        }
        if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryRegion( region), HttpStatus.OK);
        }

        List<Whisky> foundWhisky = whiskyRepository.findAll();
        return new ResponseEntity<List<Whisky>>(foundWhisky, HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhiskies(@PathVariable Long id){
        return new ResponseEntity<>(whiskyRepository, HttpStatus.OK);
    }

    @PostMapping(value = "/whiskies")
    public ResponseEntity<Whisky> postWhisky(@RequestBody Whisky whisky){
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }

    @PutMapping(value = "/whiskies/{id}")
    public ResponseEntity<Whisky> putWhisky(@RequestBody Whisky whisky, @PathVariable Long id) {
        if (whisky.getId().longValue() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/whiskies/{id}")
    public ResponseEntity<List<Whisky>> deleteWhisky(@PathVariable Long id){
        whiskyRepository.deleteById(id);
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }
}
