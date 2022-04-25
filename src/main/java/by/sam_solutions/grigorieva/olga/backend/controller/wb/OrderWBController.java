package by.sam_solutions.grigorieva.olga.backend.controller.wb;

import by.sam_solutions.grigorieva.olga.backend.domain.table.TablePage;
import by.sam_solutions.grigorieva.olga.backend.dto.wb.OrderWBDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import by.sam_solutions.grigorieva.olga.backend.service.wb.OrderWBService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderWBController {

    private final OrderWBService orderWBService;
    private final Logger logger = LoggerFactory.getLogger(OrderWBController.class);

    @GetMapping("/orders")
    public ResponseEntity<List<OrderWBDto>> getOrders(Principal principal,
                                                      @RequestParam
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                              LocalDateTime dateFrom) {
        logger.info("Getting orders...");
        return ResponseEntity.ok().body(orderWBService.getByDateFrom(dateFrom, getUser(principal)));
    }

    @GetMapping("/ordersByPage")
    public TablePage<OrderWBDto> getOrdersByPage(@RequestParam Integer shift,
                                                 @RequestParam Integer rowsPerPage,
                                                 Principal principal,
                                                 @RequestParam
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                         LocalDateTime dateFrom) {
        logger.info("Getting sales by page...");
        return orderWBService.getByShift(shift, rowsPerPage, dateFrom, getUser(principal));
    }

    private User getUser(Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
