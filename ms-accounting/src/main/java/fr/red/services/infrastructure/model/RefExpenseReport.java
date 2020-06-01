package fr.red.services.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefExpenseReport {

    private String id;
    private String type;
    private Date creationDate;
    private Double amount;
    private boolean refunded;
    private String empno;


}
