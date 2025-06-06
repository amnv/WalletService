package com.recargapay.Wallet.statement.repository;

import com.recargapay.Wallet.statement.model.StatementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StatementRepository extends JpaRepository<StatementEntity, String> {

    @Query("""
                SELECT s FROM StatementEntity s
                WHERE 1 = 1
                AND s.owner.id = :balanceId
                AND s.createdAt BETWEEN :startDate AND :endDate
            """)
    Page<StatementEntity> findAllByBalanceIdAndCreatedAtBetween(
            @Param("balanceId") String balanceId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}
