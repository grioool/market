package by.sam_solutions.grigorieva.olga.backend.entity;

import by.sam_solutions.grigorieva.olga.backend.entity.country.Country;
import by.sam_solutions.grigorieva.olga.backend.entity.town.Town;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "storage")
@Data
public class Storage extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "town_id", nullable = false)
    private Town town;
}
