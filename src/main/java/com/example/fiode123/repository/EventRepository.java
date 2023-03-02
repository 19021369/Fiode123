package com.example.fiode123.repository;

import com.example.fiode123.model.Destination;
import com.example.fiode123.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventName(String eventname);

    List<Event> findByMonth(String month);

}
