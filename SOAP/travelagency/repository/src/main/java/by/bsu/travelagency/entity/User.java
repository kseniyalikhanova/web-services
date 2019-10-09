package by.bsu.travelagency.entity;

import by.bsu.travelagency.util.QueryName;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(exclude = {"reviews","tours","version"})
@ToString(exclude = {"reviews","tours"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "travel_agency")
@SequenceGenerator(name = "user_id_seq")
@NamedQuery(name = QueryName.FIND_ALL_USERS,
            query = "SELECT user FROM User user")
@NamedQuery(name = QueryName.FIND_REVIEWS_BY_USER,
            query = "SELECT user.reviews FROM User user "
                    + "WHERE user.id=:userId")
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

    @Version
    @Column(name = "version")
    private Long version;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new LinkedList<>();

    @ManyToMany()
    @Fetch(FetchMode.JOIN)
    @JoinTable (
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
