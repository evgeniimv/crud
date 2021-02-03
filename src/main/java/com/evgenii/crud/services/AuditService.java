package com.evgenii.crud.services;

import com.evgenii.crud.entities.Audit;

import java.util.List;

public interface AuditService {
    List<Audit> getLastSpecifiedAudit(Long totalRows);
}
