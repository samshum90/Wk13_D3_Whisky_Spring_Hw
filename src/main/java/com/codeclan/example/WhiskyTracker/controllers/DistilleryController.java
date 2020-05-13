package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistillery(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "agegreaterthan", required = false) Integer age,
            @RequestParam(name = "WhiskiesNameStartsWith", required = false) String start
    ){
        if (name != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleryByName(name), HttpStatus.OK);
        }
        if (region != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleryByRegion(region), HttpStatus.OK);
        }
        if (age != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleryByWhiskiesAgeGreaterThan(age), HttpStatus.OK);
        }
        if (start != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleryByWhiskiesNameStartsWith(start), HttpStatus.OK);
        }

        List<Distillery> foundDistilleries = distilleryRepository.findAll();
        return new ResponseEntity<List<Distillery>>(foundDistilleries, HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistilleries(@PathVariable Long id){
        return new ResponseEntity<>(distilleryRepository, HttpStatus.OK);
    }

    @PostMapping(value = "/distilleries")
    public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery){
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }

    @PutMapping(value = "/distilleries/{id}")
    public ResponseEntity<Distillery> putWhisky(@RequestBody Distillery distillery, @PathVariable Long id) {
        if (distillery.getId().longValue() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/distilleries/{id}")
    public ResponseEntity<List<Distillery>> deleteDistillery(@PathVariable Long id){
        distilleryRepository.deleteById(id);
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }
}
