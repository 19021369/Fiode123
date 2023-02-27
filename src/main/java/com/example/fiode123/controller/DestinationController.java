package com.example.fiode123.controller;


import com.example.fiode123.model.Destination;
import com.example.fiode123.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api")
public class DestinationController {
    @Autowired
    DestinationRepository destinationRepository;

    // lay tat ca destination
    @GetMapping("/destinations")
    public ResponseEntity<List<Destination>> getAllDestinations(@RequestParam(required = false) String destinationname) {
        try {
            List<Destination> destinations = new ArrayList<Destination>();

            if(destinationname == null)
                destinationRepository.findAll().forEach(destinations::add);
            else
                destinationRepository.findByDestinationName(destinationname).forEach(destinations::add);

            if(destinations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(destinations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //tao destination
    @PostMapping("/destinations")
    public ResponseEntity<Destination> createUser(@RequestBody Destination destination) {
        try {
            Destination _destination = destinationRepository
                    .save(new Destination( destination.getDestinationname(), destination.getDestinationlocation(), destination.getDestinationimageurl(), destination.getDestinationintroduce(), destination.getDestinationcontent(), destination.getLocationlat(), destination.getLocationlong()));
            return new ResponseEntity<>(_destination, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //sua destination
    @PutMapping("/destinations/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable("id") long id, @RequestBody Destination destination) {
        Optional<Destination> destinationData = destinationRepository.findById(id);


        if (destinationData.isPresent()) {
            Destination _destination = destinationData.get();
            _destination.setDestinationname(destination.getDestinationname());
            _destination.setDestinationlocation(destination.getDestinationlocation());
            _destination.setDestinationimageurl(destination.getDestinationimageurl());
            _destination.setDestinationintroduce(destination.getDestinationintroduce());
            _destination.setDestinationcontent(destination.getDestinationcontent());
            _destination.setLocationlat(destination.getLocationlat());
            _destination.setLocationlong(destination.getLocationlong());
            return new ResponseEntity<>(destinationRepository.save(_destination), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // xoa destination
    @DeleteMapping("/destinations/{id}")
    public ResponseEntity<HttpStatus> deleteDestination(@PathVariable("id") long id) {
        try {
            destinationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
