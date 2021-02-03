package com.evgenii.crud.repos;

import com.evgenii.crud.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuditRepository extends JpaRepository<Audit, UUID> {

    @Query(value = "select top (:totalRows) * from dbo.audit a order by a.date desc", nativeQuery = true)
    List<Audit> getLastSpecifiedAudit(@Param("totalRows") Long totalRows);
}
