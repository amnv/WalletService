package com.recargapay.Wallet.statement.controller;

import com.recargapay.Wallet.statement.dto.StatementResponse;
import com.recargapay.Wallet.statement.mapper.StatementMapper;
import com.recargapay.Wallet.statement.service.StatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/statements")
@RequiredArgsConstructor
public class StatementController {

    private final StatementService statementService;
    private final StatementMapper statementMapper;

    @GetMapping(value = "/{balanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StatementResponse> detailBalance(
            @PathVariable String balanceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return statementService.detailBalance(balanceId, startDate, endDate, pageable)
                .map(statementMapper::toStatementResponse);
    }
}
