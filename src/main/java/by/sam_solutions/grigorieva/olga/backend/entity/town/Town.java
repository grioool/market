package by.sam_solutions.grigorieva.olga.backend.entity.town;

import by.sam_solutions.grigorieva.olga.backend.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "town")
@Data
public class Town extends AbstractEntity {

    @Column(name = "town_name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TownName townName;
}
