package com.opc.paymybuddy.dao;

import com.opc.paymybuddy.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount,String> {

    BankAccount findByIban(String iban);


}
