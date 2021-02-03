package com.evgenii.crud.controllers;

import com.evgenii.crud.entities.Audit;
import com.evgenii.crud.services.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/audit", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuditController {
    private final AuditService auditService;

    @GetMapping
    public List<Audit> getLastSpecifiedAudit(@RequestParam(value = "totalRows", required = false) Long totalRows) {
        return auditService.getLastSpecifiedAudit(totalRows);
    }
}
