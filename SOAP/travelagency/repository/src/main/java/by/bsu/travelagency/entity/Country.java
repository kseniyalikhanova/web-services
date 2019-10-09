package by.bsu.travelagency.entity;

import by.bsu.travelagency.util.QueryName;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(exclude = {"tours", "version"})
@ToString(exclude = "tours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country", schema = "travel_agency")
@SequenceGenerator(name = "country_id_seq")
@NamedNativeQuery(name = QueryName.FIND_ALL_COUNTRIES,
                  query = "SELECT c.id, c.name, C.version FROM travel_agency.country c",
                  resultClass = Country.class)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Version
    @Column(name = "version")
    private Long version;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private List<Tour> tours = new LinkedList<>();

    public Country(final Integer newId, final String newName) {
        this.id = newId;
        this.name = newName;
    }

    public Country(final String newName) {
        this.name = newName;
    }
}
