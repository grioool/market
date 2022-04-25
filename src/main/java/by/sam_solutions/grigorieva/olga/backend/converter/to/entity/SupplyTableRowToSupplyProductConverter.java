package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.SupplyTableRowDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.Storage;
import by.sam_solutions.grigorieva.olga.backend.entity.Supply;
import by.sam_solutions.grigorieva.olga.backend.entity.SupplyProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class SupplyTableRowToSupplyProductConverter implements Converter<SupplyTableRowDto, SupplyProduct> {

    private final ConversionService conversionService;

    @Override
    public SupplyProduct convert(SupplyTableRowDto supplyTableRowDto) {
        Supply supply = new Supply();
        supply.setWildberriesId(supplyTableRowDto.getWildberriesId());
        supply.setPurchase(conversionService.convert(supplyTableRowDto.getPurchase(), Purchase.class));
        supply.setStorage(conversionService.convert(supplyTableRowDto.getStorage(), Storage.class));
        supply.setDate(Timestamp.valueOf(LocalDateTime.parse(supplyTableRowDto.getDate(), DateTimeFormatter.ISO_DATE_TIME)));

        Set<SupplyProduct> supplyProducts = new HashSet<>();
        SupplyProduct supplyProduct = new SupplyProduct(supply, supplyTableRowDto.getProduct(), Integer.parseInt(supplyTableRowDto.getAmount()));
        supplyProduct.setId(supplyTableRowDto.getId());
        supplyProducts.add(supplyProduct);

        supply.setSupplyProducts(supplyProducts);
        supply.setLogistics(Double.parseDouble(supplyTableRowDto.getLogistics()));
        supply.setPurchasePrice(Double.parseDouble(supplyTableRowDto.getPurchasePrice()));
        supply.setFulfillment(Double.parseDouble(supplyTableRowDto.getFulfillment()));
        supply.setCostPrice(supplyTableRowDto.getCostPrice());
        return supplyProduct;
    }
}
