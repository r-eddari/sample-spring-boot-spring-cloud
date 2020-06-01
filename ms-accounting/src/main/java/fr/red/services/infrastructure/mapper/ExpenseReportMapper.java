package fr.red.services.infrastructure.mapper;

import fr.red.services.domain.model.ExpenseReport;
import fr.red.services.domain.model.ExpenseTypeEnum;
import fr.red.services.infrastructure.model.RefExpenseReport;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseReportMapper {

    public static List<ExpenseReport> mappingRefExpenseReportToExpenseReport(List<RefExpenseReport> refExpenseReports){
        return refExpenseReports.stream().map(refExpenseReport -> mappingRefExpenseReportToExpenseReport(refExpenseReport)).collect(Collectors.toList());
    }

    public static ExpenseReport mappingRefExpenseReportToExpenseReport(RefExpenseReport refExpenseReport){
        return ExpenseReport.builder()
                            .id(refExpenseReport.getId())
                            .type(ExpenseTypeEnum.valueOf(refExpenseReport.getType()))
                            .creationDate(refExpenseReport.getCreationDate())
                            .amount(refExpenseReport.getAmount())
                            .refunded(refExpenseReport.isRefunded())
                            .employe(refExpenseReport.getEmpno())
                            .build();
    }

    public static RefExpenseReport mappingExpenseReportToRefExpenseReport(ExpenseReport expenseReport){
        return RefExpenseReport.builder()
                               .id(expenseReport.getId())
                               .type(expenseReport.getType().toString())
                               .creationDate(expenseReport.getCreationDate())
                               .amount(expenseReport.getAmount())
                               .refunded(expenseReport.isRefunded())
                               .empno(expenseReport.getEmpno())
                               .build();
    }
}
