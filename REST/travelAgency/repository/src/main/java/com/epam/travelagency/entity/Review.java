package com.epam.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review", schema = "travel_agency")
@SequenceGenerator(name = "review_id_seq")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "is_archival", nullable = false)
    private Short isArchival = Short.valueOf("0");

    public Review(final String newText,
                  final User newUser,
                  final Tour newTour) {
        this.date = new Date(new java.util.Date().getTime());
        this.text = newText;
        this.user = newUser;
        this.tour = newTour;
    }

    public Review(final Date newDate, final String newText,
                  final User newUser, final Tour newTour) {
        this.date = newDate;
        this.text = newText;
        this.user = newUser;
        this.tour = newTour;
    }
}
