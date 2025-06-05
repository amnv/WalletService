package com.recargapay.Wallet.balance.repository;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, String> {

    Optional<BalanceEntity> findByWalletId(String walletId);
}
