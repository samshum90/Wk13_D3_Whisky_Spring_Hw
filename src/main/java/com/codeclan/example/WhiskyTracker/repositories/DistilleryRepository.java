package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistilleryRepository extends JpaRepository<Distillery, Long> {
    List<Distillery> findDistilleryByName(String name);
    List<Distillery> findDistilleryByRegion( String region);

// Get distilleries that have whiskies that are 12 years old
    List<Distillery>findDistilleryByWhiskiesAgeGreaterThan( int age);
//    Get all the distilleries that have whisky names that begin with "The"
    List<Distillery> findDistilleryByWhiskiesNameStartsWith(String name);
}
