package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.PurchaseDto;
import by.sam_solutions.grigorieva.olga.backend.dto.StorageDto;
import by.sam_solutions.grigorieva.olga.backend.dto.SupplyTableRowDto;
import by.sam_solutions.grigorieva.olga.backend.entity.SupplyProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class SupplyProductToDtoConverter implements Converter<SupplyProduct, SupplyTableRowDto> {

    private final ConversionService conversionService;

    @Override
    public SupplyTableRowDto convert(SupplyProduct supplyProduct) {
        SupplyTableRowDto supplyTableRowDto = new SupplyTableRowDto();
        supplyTableRowDto.setId(supplyProduct.getId());
        supplyTableRowDto.setWildberriesId(supplyProduct.getSupply().getWildberriesId());
        supplyTableRowDto.setPurchase(conversionService.convert(supplyProduct.getSupply().getPurchase(), PurchaseDto.class));
        supplyTableRowDto.setStorage(conversionService.convert(supplyProduct.getSupply().getStorage(), StorageDto.class));
        supplyTableRowDto.setDate(supplyProduct.getSupply().getDate().toLocalDateTime().toString());
        supplyTableRowDto.setProduct(supplyProduct.getProduct());
        supplyTableRowDto.setAmount(supplyProduct.getAmount().toString());
        supplyTableRowDto.setLogistics(supplyProduct.getSupply().getLogistics().toString());
        supplyTableRowDto.setPurchasePrice(supplyProduct.getSupply().getPurchasePrice().toString());
        supplyTableRowDto.setFulfillment(supplyProduct.getSupply().getFulfillment().toString());
        supplyTableRowDto.setCostPrice(supplyProduct.getSupply().getCostPrice());
        return supplyTableRowDto;
    }
}
