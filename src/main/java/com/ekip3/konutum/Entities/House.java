package com.ekip3.konutum.Entities;


import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "houses")
@Data
public class House {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_name")
    private String houseName;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "confidence_level")
    private Float confidenceLevel;
}
