package kr.ac.jejunu.simpleledger.transaction.service.impl;

import kr.ac.jejunu.simpleledger.transaction.dto.SimpleTransactionDto;
import kr.ac.jejunu.simpleledger.transaction.exception.TransactionNotFoundException;
import kr.ac.jejunu.simpleledger.transaction.model.SimpleTransaction;
import kr.ac.jejunu.simpleledger.transaction.repository.SimpleTransactionDao;
import kr.ac.jejunu.simpleledger.transaction.service.SimpleTransactionService;
import kr.ac.jejunu.simpleledger.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimpleTransactionServiceImpl implements SimpleTransactionService {
    private final SimpleTransactionDao simpleTransactionDao;

    @Override
    public Optional<SimpleTransaction> createTransaction(User user, SimpleTransactionDto simpleTransactionDto) {
        SimpleTransaction transaction = SimpleTransaction.builder()
                .user(user)
                .amount(simpleTransactionDto.getAmount())
                .date(simpleTransactionDto.getDate())
                .memo(simpleTransactionDto.getMemo())
                .title(simpleTransactionDto.getTitle())
                .type(simpleTransactionDto.getType())
                .build();
        return Optional.of(simpleTransactionDao.save(transaction));
    }

    @Override
    public Optional<SimpleTransaction> updateTransaction(Integer id, SimpleTransactionDto simpleTransactionDto) throws TransactionNotFoundException {
        Optional<SimpleTransaction> transaction = simpleTransactionDao.findById(id);
        if(transaction.isPresent()){
            SimpleTransaction updatedTransaction = transaction.get();
            updatedTransaction.setAmount(simpleTransactionDto.getAmount());
            updatedTransaction.setDate(simpleTransactionDto.getDate());
            updatedTransaction.setTitle(simpleTransactionDto.getTitle());
            updatedTransaction.setMemo(simpleTransactionDto.getMemo());
            updatedTransaction.setType(simpleTransactionDto.getType());
            return Optional.of(simpleTransactionDao.save(updatedTransaction));
        }
        else{
            throw new TransactionNotFoundException();
        }
    }

    @Override
    public void deleteTransaction(Integer id) throws TransactionNotFoundException {
        Optional<SimpleTransaction> optionalTransaction = simpleTransactionDao.findById(id);
        if (optionalTransaction.isPresent()) {
            simpleTransactionDao.delete(optionalTransaction.get());
        }
        else{
            throw new TransactionNotFoundException();
        }
    }

    @Override
    public Optional<List<SimpleTransaction>> findTransactionsByUser(User user) {
        return simpleTransactionDao.findAllByUser(user);
    }
}
