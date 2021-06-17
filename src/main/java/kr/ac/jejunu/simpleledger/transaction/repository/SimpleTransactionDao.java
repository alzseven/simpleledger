package kr.ac.jejunu.simpleledger.transaction.repository;

import kr.ac.jejunu.simpleledger.transaction.model.SimpleTransaction;
import kr.ac.jejunu.simpleledger.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SimpleTransactionDao extends JpaRepository<SimpleTransaction, Integer> {
    Optional<List<SimpleTransaction>> findAllByUser(User user);
}
