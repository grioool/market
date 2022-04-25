package by.sam_solutions.grigorieva.olga.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "subscription")
@Data
public class Subscription extends AbstractEntity {

    @Column(name = "date_start", nullable = false)
    private Timestamp dateStart;

    @Column(name = "date_end", nullable = false)
    private Timestamp dateEnd;

    @Column(name = "period", nullable = false)
    private Integer period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
