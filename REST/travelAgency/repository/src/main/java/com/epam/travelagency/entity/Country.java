package com.epam.travelagency.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country", schema = "travel_agency")
@SequenceGenerator(name = "country_id_seq")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "is_archival", nullable = false)
    private Short isArchival = Short.valueOf("0");

    public Country(final String newName) {
        this.name = newName;
    }
}
