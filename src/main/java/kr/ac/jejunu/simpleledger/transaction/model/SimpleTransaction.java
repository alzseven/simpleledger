package kr.ac.jejunu.simpleledger.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.jejunu.simpleledger.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnore
    private User user;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String date;
    private Integer amount;
    private String title;
    private String memo;
}
