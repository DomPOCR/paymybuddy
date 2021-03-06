package com.opc.paymybuddy.service;

import com.opc.paymybuddy.model.BankAccount;

import java.util.List;

public interface BankAccountService {

     List<BankAccount> findAll();

     BankAccount findByIban(String iban);

     BankAccount  addBankAccount(BankAccount addAccount, Integer userId) throws Exception;



}
