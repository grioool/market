package by.sam_solutions.grigorieva.olga.backend.dto;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class PurchaseDto {

    @NotNull
    private int id;

    @NotNull
    private String date;

    @NotNull
    private String productName;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String priceForOne;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String amount;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String purchasePrice;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String logistics;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String costPrice;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String batchPrice;

    @CustomPattern(patternKey = "field.digits.regexp", message = "field.digits.invalid")
    @NotNull
    private String extra;
}
