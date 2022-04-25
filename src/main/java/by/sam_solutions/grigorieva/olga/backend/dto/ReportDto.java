package by.sam_solutions.grigorieva.olga.backend.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ReportDto {

    private Integer id;

    private SupplyTableRowDto supply;

    private Long orderNumber;

    private String name;

    private Double orderPrice;

    private Double proceeds;

    private Double logistics;

    private Double costPrice;

    private Double commission;

    private Double profit;

    private Double commissionPerCent;

    private String dateSale;

    private String dateOrder;
}
