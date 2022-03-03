package com.example.cqrseslearning.account.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransferRepository extends JpaRepository<BankTransferEntity, Long> {

  BankTransferEntity findByAxonBankTransferId(String id);

}
