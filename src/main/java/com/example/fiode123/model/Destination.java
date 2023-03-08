package com.example.fiode123.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table (name = "destinations")
@Data
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    public String destinationname;

    @Column(nullable = false, length = 100)
    public String destinationlocation;

    @Column(nullable = false, length = 300)
    public String destinationimageurl;

    @Column(nullable = false, length = 1000)
    public String destinationintroduce;

    @Column(nullable = false, length = 5000)
    public String destinationcontent;

    @Column(nullable = false, length = 50)
    public Double locationlat;

    @Column(nullable = false, length = 50)
    public Double locationlong;

    public Destination(String destinationname, String destinationlocation, String destinationimageurl, String destinationintroduce, String destinationcontent, Double locationlat, Double locationlong) {
        this.destinationname = destinationname;
        this.destinationlocation = destinationlocation;
        this.destinationimageurl = destinationimageurl;
        this.destinationintroduce = destinationintroduce;
        this.destinationcontent = destinationcontent;
        this.locationlat = locationlat;
        this.locationlong = locationlong;
    }

    public Destination() {}
}
