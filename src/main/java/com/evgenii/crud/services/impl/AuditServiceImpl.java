package com.evgenii.crud.services.impl;

import com.evgenii.crud.entities.Audit;
import com.evgenii.crud.repos.AuditRepository;
import com.evgenii.crud.services.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public List<Audit> getLastSpecifiedAudit(Long totalRows) {
        return auditRepository.getLastSpecifiedAudit(Optional.ofNullable(totalRows).orElse(100L));
    }
}
