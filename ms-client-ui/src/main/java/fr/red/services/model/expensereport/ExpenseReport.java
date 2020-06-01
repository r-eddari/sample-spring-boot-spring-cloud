package fr.red.services.model.expensereport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseReport {

    private String id;
    private String type;
    private Date creationDate;
    private Double amount;
    private boolean refunded;
    private String empno;
}
