package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.PurchaseDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseToEntityConverter implements Converter<PurchaseDto, Purchase> {

    @Override
    public Purchase convert(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseDto.getId());
        purchase.setDate(Timestamp.valueOf(LocalDateTime.parse(purchaseDto.getDate(), DateTimeFormatter.ISO_DATE_TIME)));
        purchase.setProductName(purchaseDto.getProductName());
        purchase.setPriceForOne(Double.parseDouble(purchaseDto.getPriceForOne()));
        purchase.setAmount(Integer.parseInt(purchaseDto.getAmount()));
        purchase.setPurchasePrice(Integer.parseInt(purchaseDto.getPurchasePrice()));
        purchase.setLogistics(Double.parseDouble(purchaseDto.getLogistics()));
        purchase.setCostPrice(Double.parseDouble(purchaseDto.getCostPrice()));
        purchase.setBatchPrice(Double.parseDouble(purchaseDto.getBatchPrice()));
        purchase.setExtra(Double.parseDouble(purchaseDto.getExtra()));
        return purchase;
    }
}