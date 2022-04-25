package by.sam_solutions.grigorieva.olga.backend.dto.wb;

import lombok.Data;

import java.util.Date;

@Data
public class OrderWBDto {

    private Long number;

    private Date date;

    private Date lastChangeDate;

    private String supplierArticle;

    private String techSize;

    private String barcode;

    private Long quantity;

    private Long totalPrice;

    private Long discountPercent;

    private String warehouseName;

    private String oblast;

    private Long incomeID;

    private Long odid;

    private Long nmId;

    private String subject;

    private String category;

    private String brand;

    private Boolean isCancel;

    private String cancel_dt;

    private String gNumber;

}
