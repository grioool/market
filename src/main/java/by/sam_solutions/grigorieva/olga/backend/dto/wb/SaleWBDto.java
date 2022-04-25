package by.sam_solutions.grigorieva.olga.backend.dto.wb;

import lombok.Data;

import java.util.Date;

@Data
public class SaleWBDto {

    private String number;

    private Date date;

    private Date lastChangeDate;

    private String supplierArticle;

    private String techSize;

    private String barcode;

    private Long quantity;

    private Long totalPrice;

    private Long discountPercent;

    private Boolean isSupply;

    private Boolean isRealization;

    private Long orderId;

    private Long promoCodeDiscount;

    private String warehouseName;

    private String countryName;

    private String oblastOkrugName;

    private String regionName;

    private Long incomeID;

    private String saleID;

    private Long odid;

    private Long spp;

    private Double forPay;

    private Double finishedPrice;

    private Long priceWithDisc;

    private Long nmId;

    private String subject;

    private String category;

    private String brand;

    private Long IsStorno;

    private String gNumber;
}