package com.example.fiode123.repository;

import java.util.List;

import com.example.fiode123.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findByDestinationName(String destinationname);

}
