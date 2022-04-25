package by.sam_solutions.grigorieva.olga.backend.entity.country;

import by.sam_solutions.grigorieva.olga.backend.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Data
public class Country extends AbstractEntity {

    @Column(name = "country_name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CountryName countryName;

}
