package by.sam_solutions.grigorieva.olga.backend.domain.constraint;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DatePeriod {

    private final LocalDateTime from;

    private final LocalDateTime to;
}
