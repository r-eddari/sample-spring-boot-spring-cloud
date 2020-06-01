package fr.red.services.domain.port.infrastructure;

import fr.red.services.domain.model.ExpenseReport;

import java.util.List;

public interface ExpenseReportDataGateway {

    List<ExpenseReport> getExpenseReportList();
    List<ExpenseReport> getExpenseReportListByEmpno(String empno);
    ExpenseReport getExpenseReportById(String id);
    ExpenseReport addExpenseReport(ExpenseReport expenseReport);
    ExpenseReport updateExpenseReport(ExpenseReport expenseReport);
    void deleteExpenseReport(ExpenseReport expenseReport);
}
