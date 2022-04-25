package by.sam_solutions.grigorieva.olga.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "supply_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplyProduct extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "supply_id", nullable = false)
    private Supply supply;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "amount", nullable = false)
    private Integer amount;

}
