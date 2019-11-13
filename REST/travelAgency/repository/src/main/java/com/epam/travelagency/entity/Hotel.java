package com.epam.travelagency.entity;

import com.epam.travelagency.entity.enumeration.Feature;
import com.epam.travelagency.entity.sqltype.PostgreSqlFeatureType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel", schema = "travel_agency")
@TypeDef(name = "features",
        typeClass = PostgreSqlFeatureType.class)
@SequenceGenerator(name = "hotel_id_seq")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "stars", nullable = false)
    private short stars;

    @Column(name = "website", nullable = false, length = 63)
    private String website;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "features", columnDefinition = "feature[]")
    @Enumerated(EnumType.STRING)
    @Type(type = "features")
    private List<Feature> features;

    @Column(name = "is_archival", nullable = false)
    private Short isArchival = Short.valueOf("0");

    public Hotel(final String newName, final short newStars,
                 final String newWebsite, final double newLatitude,
                 final double newLongitude, final List<Feature> newFeatures) {
        this.name = newName;
        this.stars = newStars;
        this.website = newWebsite;
        this.latitude = newLatitude;
        this.longitude = newLongitude;
        this.features = newFeatures;
    }
}
