package com.epam.travelagency.entity;

import com.epam.travelagency.entity.enumeration.TourType;
import com.epam.travelagency.entity.sqltype.PostgreSqlTourType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour", schema = "travel_agency")
@TypeDef(name = "postgre_enum", typeClass = PostgreSqlTourType.class)
@SequenceGenerator(name = "tour_id_seq")
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

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_tour",
            inverseJoinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> users = new LinkedList<>();

    @Column(name = "is_archival", nullable = false)
    private Short isArchival;

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
