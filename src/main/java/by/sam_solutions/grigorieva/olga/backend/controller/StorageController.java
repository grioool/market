package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.dto.StorageDto;
import by.sam_solutions.grigorieva.olga.backend.dto.UserDto;
import by.sam_solutions.grigorieva.olga.backend.service.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@Validated
public class StorageController {

    private final StorageService storageService;
    private final Logger logger = LoggerFactory.getLogger(StorageController.class);
    private final ConversionService conversionService;

    @GetMapping("/storages")
    public ResponseEntity<List<StorageDto>> getUsers() {
        logger.info("Getting users...");
        return new ResponseEntity<>(storageService.getAll().stream()
                .map(storage -> conversionService.convert(storage, StorageDto.class))
                .collect(toList()), HttpStatus.OK);
    }
}
