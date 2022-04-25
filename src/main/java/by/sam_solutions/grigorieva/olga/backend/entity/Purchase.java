package by.sam_solutions.grigorieva.olga.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "purchase")
@Data
public class Purchase extends AbstractEntity {
    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price_for_one", nullable = false)
    private Double priceForOne;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;

    @Column(name = "logistics", nullable = false)
    private Double logistics;

    @Column(name = "cost_price", nullable = false)
    private Double costPrice;

    @Column(name = "batch_price", nullable = false)
    private Double batchPrice;

    @Column(name = "extra", nullable = false)
    private Double extra;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

