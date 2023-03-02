package com.example.fiode123.model;


import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(name = "regions")
@Data
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 10)
    public String regionname;

    @Column(nullable = false, unique = true, length = 300)
    public String image;

    @Column(nullable = false, length = 100)
    public String side;

    @Column(nullable = false, length = 5000)
    public String regiondesc;

    @Column(nullable = false, length = 5000)
    public String regionweather;

    @Column(nullable = false, length = 5000)
    public String regiontransportation;

    public Region(String regionname, String image, String side, String regiondesc, String regionweather, String regiontransportation) {
        this.regionname = regionname;
        this.image = image;
        this.side = side;
        this.regiondesc = regiondesc;
        this.regionweather = regionweather;
        this.regiontransportation = regiontransportation;
    }

    public Region() {
    }

}
