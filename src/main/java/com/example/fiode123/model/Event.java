package com.example.fiode123.model;

import lombok.Data;

import java.sql.Timestamp;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 10)
    public String eventname;

    @Column(nullable = false, unique = true, length = 300)
    public String image;

    @Column(nullable = false, length = 100)
    public String region;

    @Column(nullable = false, length = 100)
    public String month;

    @Column(nullable = false)
    public Timestamp timestart;

    @Column(nullable = false)
    public Timestamp timeend;

    @Column(nullable = false, length = 10000)
    public String eventdesc;


    public Event(String eventname, String image, String region, String month, Timestamp timestart, Timestamp timeend, String eventdesc) {
        this.eventname = eventname;
        this.image = image;
        this.region = region;
        this.month = month;
        this.timestart = timestart;
        this.timeend = timeend;
        this.eventdesc = eventdesc;
    }

    public Event() {
    }

}
