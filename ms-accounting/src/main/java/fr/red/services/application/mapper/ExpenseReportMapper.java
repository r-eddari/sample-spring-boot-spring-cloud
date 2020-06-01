package fr.red.services.application.mapper;

import fr.red.services.application.model.ExpenseReportUI;
import fr.red.services.domain.model.ExpenseReport;
import fr.red.services.domain.model.ExpenseTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseReportMapper {

    public static List<ExpenseReportUI> mappingExpenseReportToExpenseReportUI(List<ExpenseReport> expenseReports){
        return expenseReports.stream().map(expenseReport -> mappingExpenseReportToExpenseReportUI(expenseReport)).collect(Collectors.toList());
    }

    public static ExpenseReportUI mappingExpenseReportToExpenseReportUI(ExpenseReport expenseReport){
        return ExpenseReportUI.builder()
                              .id(expenseReport.getId())
                              .type(expenseReport.getType().toString())
                              .creationDate(expenseReport.getCreationDate())
                              .amount(expenseReport.getAmount())
                              .refunded(expenseReport.isRefunded())
                              .empno(expenseReport.getEmpno())
                              .build();
    }

    public static ExpenseReport mappingExpenseReportUIToExpenseReport(ExpenseReportUI expenseReportUI){
        return ExpenseReport.builder()
                            .id(expenseReportUI.getId())
                            .type(ExpenseTypeEnum.valueOf(expenseReportUI.getType()))
                            .creationDate(expenseReportUI.getCreationDate())
                            .amount(expenseReportUI.getAmount())
                            .refunded(expenseReportUI.isRefunded())
                            .employe(expenseReportUI.getEmpno())
                            .build();
    }
}
