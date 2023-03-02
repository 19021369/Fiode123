package com.example.fiode123.controller;


import com.example.fiode123.model.Destination;
import com.example.fiode123.model.Region;
import com.example.fiode123.repository.DestinationRepository;
import com.example.fiode123.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegionController {
    @Autowired
    RegionRepository regionRepository;

    // lay regions theo mien lam trang region
    @GetMapping("/regions/{sitename}")
    public ResponseEntity<List<Region>> getAllRegions(@RequestParam(required = false) String sitename) {
        try {
            List<Region> regions = new ArrayList<Region>();

            regionRepository.findBySiteName(sitename).forEach(regions::add);

            if(regions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(regions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // thanh search
    @GetMapping("/regions/{regionname}")
    public ResponseEntity<List<Region>> getRegionByName(@RequestParam(required = false) String regionname) {
        try {
            List<Region> regions = new ArrayList<Region>();

            regionRepository.findByRegionName(regionname).forEach(regions::add);

            if(regions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(regions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //sua region
    @PutMapping("/regions/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable("id") long id, @RequestBody Region region) {
        Optional<Region> regionData = regionRepository.findById(id);
        if (regionData.isPresent()) {
            Region _region = regionData.get();
            _region.setRegiondesc(region.getRegiondesc());
            _region.setSide(region.getSide());
            _region.setImage(region.getImage());
            _region.setRegionname(region.getRegionname());
            _region.setRegiontransportation(region.getRegiontransportation());
            _region.setRegionweather(region.getRegionweather());
            return new ResponseEntity<>(regionRepository.save(_region), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // xoa region
    @DeleteMapping("/regions/{id}")
    public ResponseEntity<HttpStatus> deleteDestination(@PathVariable("id") long id) {
        try {
            regionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
