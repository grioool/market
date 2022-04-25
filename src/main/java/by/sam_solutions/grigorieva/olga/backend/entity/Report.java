package by.sam_solutions.grigorieva.olga.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supply_id", nullable = false)
    private Supply supply;

    @Column(name = "order_number", nullable = false)
    private Long orderNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order_price", nullable = false)
    private Double orderPrice;

    @Column(name = "proceeds", nullable = false)
    private Double proceeds;

    @Column(name = "logistics", nullable = false)
    private Double logistics;

    @Column(name = "cost_price", nullable = false)
    private Double costPrice;

    @Column(name = "commission", nullable = false)
    private Double commission;

    @Column(name = "profit", nullable = false)
    private Double profit;

    @Column(name = "commission_per_cent", nullable = false)
    private Double commissionPerCent;

    @Column(name = "date_sale", nullable = false)
    private Timestamp dateSale;

    @Column(name = "date_order", nullable = false)
    private Timestamp dateOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
