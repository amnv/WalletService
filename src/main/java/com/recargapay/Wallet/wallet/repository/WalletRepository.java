package com.recargapay.Wallet.wallet.repository;

import com.recargapay.Wallet.wallet.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {
}
