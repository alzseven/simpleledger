package kr.ac.jejunu.simpleledger.transaction.dto;

import kr.ac.jejunu.simpleledger.transaction.model.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleTransactionDto {
    private TransactionType type;
    private Integer amount;
    private String title;
    private String memo;
    private String date;
}
