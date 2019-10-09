package by.bsu.travelagency.entity;

import by.bsu.travelagency.entity.enumeration.Feature;
import by.bsu.travelagency.entity.sqltype.PostgreSqlFeatureType;
import by.bsu.travelagency.util.QueryName;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel", schema = "travel_agency")
@TypeDef(name = "features",
         typeClass = PostgreSqlFeatureType.class)
@SequenceGenerator(name = "hotel_id_seq")
@NamedQuery(name = QueryName.FIND_ALL_HOTELS,
                  query = "SELECT hotel FROM Hotel hotel")
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

    @Version
    @EqualsAndHashCode.Exclude
    @Column(name = "version")
    private Long version;

    @Column(name = "features", columnDefinition = "feature[]")
    @Enumerated(EnumType.STRING)
    @Type(type = "features")
    private List<Feature> features;

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

    public Hotel(final Integer newId, final String newName, final short newStars,
                 final String newWebsite, final double newLatitude,
                 final double newLongitude, final List<Feature> newFeatures) {
        this.id = newId;
        this.name = newName;
        this.stars = newStars;
        this.website = newWebsite;
        this.latitude = newLatitude;
        this.longitude = newLongitude;
        this.features = newFeatures;
    }
}
