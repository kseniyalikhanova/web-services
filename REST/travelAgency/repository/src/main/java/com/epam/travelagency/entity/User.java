package com.epam.travelagency.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(exclude = {"tours"})
@ToString(exclude = {"tours"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "travel_agency")
@SequenceGenerator(name = "user_id_seq")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login", nullable = false)
    @Size(min = 4, max = 100)
    @NotNull
    private String login;

    @Column(name = "password", nullable = false)
    @NotNull
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])\\w{6,20}")
    private String password;

    @Column(name = "is_admin", nullable = false)
    private short isAdmin;

    @ManyToMany()
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "user_tour",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id", referencedColumnName = "id")
    )
    private List<Tour> tours = new LinkedList<>();

    public User(final Integer newId, final String newLogin, final String newPassword) {
        this.id = newId;
        this.login = newLogin;
        this.password = newPassword;
    }

    public User(final String newLogin, final String newPassword) {
        this.login = newLogin;
        this.password = newPassword;
    }
}
