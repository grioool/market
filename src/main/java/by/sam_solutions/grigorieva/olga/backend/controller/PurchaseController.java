package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.PurchaseDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Purchase;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.purchase.PurchaseService;
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
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
    private final ConversionService conversionService;

    @GetMapping(value = "/purchases")
    public  ResponseEntity<List<PurchaseDto>> getPurchases(Principal principal) {
        logger.info("Getting purchases...");
        return new ResponseEntity<>(purchaseService.getByUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).stream()
                .map(purchase -> conversionService.convert(purchase, PurchaseDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/purchasesByPage")
    public TablePage<PurchaseDto> getPurchasesByPage(@RequestParam Integer shift, @RequestParam Integer rowsPerPage, Principal principal) {
        logger.info("Getting purchases by page...");
        TablePage<Purchase> page = purchaseService.getPurchasesPerPage(getUser(principal), shift, rowsPerPage);
        return new TablePage<>(
                page.getItems().stream()
                        .map(purchase -> conversionService.convert(purchase, PurchaseDto.class))
                        .collect(Collectors.toList()),
                page.getTotalCount(),
                page.getCurrentShift()
        );
    }

    @GetMapping(value = "/purchases/{purchaseId}")
    public  ResponseEntity<PurchaseDto> getPurchase(@PathVariable("purchaseId") int id) {
        logger.info("Getting purchase...");
        return new ResponseEntity<>(conversionService.convert(purchaseService.getById(id), PurchaseDto.class), HttpStatus.OK);
    }

    @PostMapping(value = "/purchases")
    public  ResponseEntity<PurchaseDto> create(@RequestBody @Valid PurchaseDto purchaseDto, Principal principal) {
        logger.info("Creating purchase...");
        Purchase purchase = conversionService.convert(purchaseDto, Purchase.class);
        purchase.setUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return new ResponseEntity<>(conversionService.convert(purchaseService.create(purchase), PurchaseDto.class), HttpStatus.OK);
    }

    @PutMapping(value = "/purchases")
    public  ResponseEntity<PurchaseDto> update(@RequestBody PurchaseDto purchaseDto, Principal principal) {
        logger.info("Updating purchase...");
        Purchase purchase = conversionService.convert(purchaseDto, Purchase.class);
        purchase.setUser((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return new ResponseEntity<>(conversionService.convert(purchaseService.update(purchase), PurchaseDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/purchases/{purchaseId}")
    public void delete(@PathVariable("purchaseId") int id) {
        logger.info("Deleting purchase...");
        purchaseService.delete(id);
        logger.info("Deleted.");
    }

    private User getUser(Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

}