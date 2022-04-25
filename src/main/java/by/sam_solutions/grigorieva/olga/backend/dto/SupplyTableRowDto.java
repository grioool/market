package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class SupplyTableRowDto {

    @NotNull
    private Integer id;

    @NotNull
    private Integer wildberriesId;

    @NotNull
    private PurchaseDto purchase;

    @NotNull
    private StorageDto storage;

    @NotNull
    private String date;

    @NotNull
    private String product;

    @NotNull
    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    private String amount;

    @NotNull
    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    private String logistics;

    @NotNull
    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    private String purchasePrice;

    @NotNull
    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    private String fulfillment;

    @NotNull
    private Double costPrice;

    public static SupplyTableRowDto blank(Integer id) {
        SupplyTableRowDto supplyTableRowDto = new SupplyTableRowDto();
        supplyTableRowDto.setId(id);
        supplyTableRowDto.setCostPrice(-1.0);
        return supplyTableRowDto;
    }
}
