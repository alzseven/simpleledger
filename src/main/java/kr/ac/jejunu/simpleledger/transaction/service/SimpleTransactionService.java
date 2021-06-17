package kr.ac.jejunu.simpleledger.transaction.service;

import kr.ac.jejunu.simpleledger.transaction.dto.SimpleTransactionDto;
import kr.ac.jejunu.simpleledger.transaction.exception.TransactionNotFoundException;
import kr.ac.jejunu.simpleledger.transaction.model.SimpleTransaction;
import kr.ac.jejunu.simpleledger.user.model.User;

import java.util.List;
import java.util.Optional;

public interface SimpleTransactionService {

    Optional<SimpleTransaction> createTransaction(User user, SimpleTransactionDto simpleTransactionDto);

    Optional<SimpleTransaction> updateTransaction(Integer id, SimpleTransactionDto simpleTransactionDto) throws TransactionNotFoundException;

    void deleteTransaction(Integer id) throws TransactionNotFoundException;

    Optional<List<SimpleTransaction>> findTransactionsByUser(User user);
}
