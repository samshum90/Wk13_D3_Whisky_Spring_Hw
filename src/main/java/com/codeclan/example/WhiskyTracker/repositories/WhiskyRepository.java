package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;

@Repository
public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByName(String name);

    List<Whisky> findByYear(int year);

    List<Whisky> findByAge(int age);

    List<Whisky> findWhiskyByDistilleryRegion(String region);

    List<Whisky> findWhiskyByNameContaining(String name);
//Get all the whiskies that begin with "The"
    List<Whisky> findWhiskyByNameStartsWith(String name);

}
