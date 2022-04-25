package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.SupplyTableRowDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Supply;
import by.sam_solutions.grigorieva.olga.backend.entity.SupplyProduct;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.supply.SupplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
public class SupplyController {

    private final SupplyService supplyService;
    private final Logger logger = LoggerFactory.getLogger(SupplyController.class);
    private final ConversionService conversionService;

    @GetMapping(value = "/supplies")
    public ResponseEntity<List<SupplyTableRowDto>> getSupplies(Principal principal) {
        logger.info("Getting supplies...");
        return new ResponseEntity<>(supplyService.getByUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).stream()
                .map(supply -> conversionService.convert(supply, SupplyTableRowDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/supplies/ids")
    public ResponseEntity<List<Integer>> getSuppliesIds(Principal principal) {
        logger.info("Getting supplies ids...");
        return new ResponseEntity<>(supplyService.getByUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).stream()
                .map(Supply::getWildberriesId)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/suppliesByPage")
    public TablePage<SupplyTableRowDto> getSuppliessByPage(@RequestParam Integer shift, @RequestParam Integer rowsPerPage, Principal principal) {
        logger.info("Getting supplies by page...");
        TablePage<SupplyProduct> page = supplyService.getSupplyProductsPerPage(getUser(principal), shift, rowsPerPage);
        return new TablePage<>(
                page.getItems().stream()
                        .map(supplyProduct -> conversionService.convert(supplyProduct, SupplyTableRowDto.class))
                        .collect(Collectors.toList()),
                page.getTotalCount(),
                page.getCurrentShift()
        );
    }

    @GetMapping(value = "/supplies/{supplyId}")
    public ResponseEntity<SupplyTableRowDto> getSupply(@PathVariable("supplyId") int id) {
        logger.info("Getting supply...");
        return new ResponseEntity<>(conversionService.convert(supplyService.getById(id), SupplyTableRowDto.class), HttpStatus.OK);
    }

    @PostMapping(value = "/supplies")
    public ResponseEntity<SupplyTableRowDto> create(@RequestBody @Valid SupplyTableRowDto supplyTableRowDto, Principal principal) {
        logger.info("Creating supply...");
        SupplyProduct supplyProduct = conversionService.convert(supplyTableRowDto, SupplyProduct.class);
        supplyProduct.getSupply().setUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        supplyService.addSupplyProduct(supplyProduct);
        return new ResponseEntity<>(supplyTableRowDto, HttpStatus.OK);
    }

    @PutMapping(value = "/supplies")
    public ResponseEntity<SupplyTableRowDto> update(@RequestBody SupplyTableRowDto supplyTableRowDto, Principal principal) {
        logger.info("Updating supply...");
        SupplyProduct supplyProduct = conversionService.convert(supplyTableRowDto, SupplyProduct.class);
        supplyProduct.getSupply().setUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        supplyService.updateSupplyProduct(supplyProduct);
        return ResponseEntity.ok(supplyTableRowDto);
    }

    @DeleteMapping(value = "/supplies/{supplyId}")
    public void delete(@PathVariable("supplyId") int id) {
        logger.info("Deleting supply...");
        supplyService.deleteSupplyProduct(id);
        logger.info("Deleted.");
    }

    private User getUser(Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

}
