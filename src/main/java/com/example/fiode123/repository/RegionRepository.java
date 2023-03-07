package com.example.fiode123.repository;

import com.example.fiode123.model.Event;
import com.example.fiode123.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findBySiteName(String sitename);
    List<Region> findByRegionName(String regionname);
}
