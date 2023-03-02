package com.example.fiode123.controller;


import com.example.fiode123.model.Event;
import com.example.fiode123.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    EventRepository eventRepository;

    // lay events thanh search
    @GetMapping("/events/{eventname}")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String eventname) {
        try {
            List<Event> events = new ArrayList<Event>();

            eventRepository.findByEventName(eventname).forEach(events::add);

            if(events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // event theo thang
    @GetMapping("/events/{month}")
    public ResponseEntity<List<Event>> getEventByMonth(@RequestParam(required = false) String month) {
        try {
            List<Event> events = new ArrayList<Event>();

            eventRepository.findByMonth(month).forEach(events::add);

            if(events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //sua event
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
        Optional<Event> eventData = eventRepository.findById(id);
        if (eventData.isPresent()) {
            Event _event = eventData.get();
            _event.setRegion(_event.getRegion());
            _event.setEventname(event.getEventname());
            _event.setEventdesc(event.getEventdesc());
            _event.setMonth(event.getMonth());
            _event.setImage(event.getImage());
            _event.setTimeend(event.getTimeend());
            _event.setTimestart(event.getTimestart());
            return new ResponseEntity<>(eventRepository.save(_event), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // xoa event
    @DeleteMapping("/events/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
        try {
            eventRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
