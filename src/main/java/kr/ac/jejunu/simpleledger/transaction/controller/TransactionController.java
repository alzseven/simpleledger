package kr.ac.jejunu.simpleledger.transaction.controller;

import kr.ac.jejunu.simpleledger.security.JwtAuthTokenProvider;
import kr.ac.jejunu.simpleledger.transaction.dto.SimpleTransactionDto;
import kr.ac.jejunu.simpleledger.transaction.exception.*;
import kr.ac.jejunu.simpleledger.transaction.model.SimpleTransaction;
import kr.ac.jejunu.simpleledger.transaction.service.SimpleTransactionService;
import kr.ac.jejunu.simpleledger.user.model.User;
import kr.ac.jejunu.simpleledger.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final SimpleTransactionService simpleTransactionService;
    private final UserService userService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestHeader("Bearer") String token ,@RequestBody SimpleTransactionDto createTransactionDto)
            throws CreateTransactionFailedException, UserNotFoundException {
        Optional<User> user = userService.findUserByUsername(jwtAuthTokenProvider.getUsernameFromToken(token));
        if (user.isPresent()) {
            Optional<SimpleTransaction> transaction = simpleTransactionService.createTransaction(user.get(), createTransactionDto);
            if(transaction.isPresent()){
                return ResponseEntity.ok("Successfully created");
            }
            else{
                throw new CreateTransactionFailedException();
            }
        }
        else{
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTransactionsByUsername(@RequestHeader("Bearer") String token) throws UserNotFoundException {
        Optional<User> user = userService.findUserByUsername(jwtAuthTokenProvider.getUsernameFromToken(token));
        if (user.isPresent()) {
            Optional<List<SimpleTransaction>> simpleTransactions = simpleTransactionService.findTransactionsByUser(user.get());
            if(simpleTransactions.isPresent()){
                return ResponseEntity.ok(simpleTransactions.get());
            }
            else{
                return ResponseEntity.ok().build();
            }
        }
        else{
            throw new UserNotFoundException();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTransaction(@RequestParam(name = "id") Integer id, @ModelAttribute SimpleTransactionDto simpleTransactionDto)
            throws UpdateTransactionFailedException, TransactionNotFoundException {
        final Optional<SimpleTransaction> transaction = simpleTransactionService.updateTransaction(id, simpleTransactionDto);
        if (transaction.isPresent()) {
            return ResponseEntity.ok("successfully updated");
        } else {
            throw new UpdateTransactionFailedException();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTransaction(@RequestParam(name = "id") Integer id)
            throws DeleteTransactionFailedException {
        try{
            simpleTransactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            throw new DeleteTransactionFailedException();
        }
    }
}