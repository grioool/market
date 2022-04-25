package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.PurchaseDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import org.springframework.core.convert.converter.Converter;

public class PurchaseToDtoConverter implements Converter<Purchase, PurchaseDto> {

    @Override
    public PurchaseDto convert(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setDate(purchase.getDate().toLocalDateTime().toString());
        purchaseDto.setProductName(purchase.getProductName());
        purchaseDto.setPriceForOne(purchase.getPriceForOne().toString());
        purchaseDto.setAmount(purchase.getAmount().toString());
        purchaseDto.setPurchasePrice(purchase.getPurchasePrice().toString());
        purchaseDto.setLogistics(purchase.getLogistics().toString());
        purchaseDto.setCostPrice(purchase.getCostPrice().toString());
        purchaseDto.setBatchPrice(purchase.getBatchPrice().toString());
        purchaseDto.setExtra(purchase.getExtra().toString());
        return purchaseDto;
    }
}
