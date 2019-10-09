package by.bsu.travelagency.entity;

import by.bsu.travelagency.entity.enumeration.TourType;
import by.bsu.travelagency.entity.sqltype.PostgreSqlTourType;
import by.bsu.travelagency.util.QueryName;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(exclude = {"reviews","users","version"})
@ToString(exclude = {"reviews","users"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour", schema = "travel_agency")
@TypeDef(name = "postgre_enum", typeClass = PostgreSqlTourType.class)
@SequenceGenerator(name = "tour_id_seq")
@NamedQuery(name = QueryName.FIND_ALL_TOURS,
            query = "SELECT tour FROM Tour tour")
@NamedQuery(name = QueryName.FIND_REVIEWS_BY_TOUR,
        query = "SELECT tour.reviews FROM Tour tour "
                + "WHERE tour.id=:tourId ORDER BY tour.date")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "duration", nullable = false)
    private short duration;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "tour_type")
    @Enumerated(EnumType.STRING)
    @Type(type = "postgre_enum")
    private TourType tourType;

    @Version
    @Column(name = "version")
    private Long version;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "tour")
    @Fetch(FetchMode.JOIN)
    private List<Review> reviews = new LinkedList<>();

    @ManyToMany(mappedBy = "tours")
    private List<User> users = new LinkedList<>();

    public Tour(final Integer newId, final String newPhoto, final Date newDate,
                final short newDuration, final String newDescription, final BigDecimal newCost,
                final TourType newTourType, final Hotel newHotel, final Country newCountry) {
        this.id = newId;
        this.photo = newPhoto;
        this.date = newDate;
        this.duration = newDuration;
        this.description = newDescription;
        this.cost = newCost;
        this.tourType = newTourType;
        this.hotel = newHotel;
        this.country = newCountry;
    }

    public Tour(final String newPhoto, final Date newDate,
                final short newDuration, final String newDescription,
                final BigDecimal newCost, final TourType newTourType,
                final Hotel newHotel, final Country newCountry) {
        this.photo = newPhoto;
        this.date = newDate;
        this.duration = newDuration;
        this.description = newDescription;
        this.cost = newCost;
        this.tourType = newTourType;
        this.hotel = newHotel;
        this.country = newCountry;

    }
}
