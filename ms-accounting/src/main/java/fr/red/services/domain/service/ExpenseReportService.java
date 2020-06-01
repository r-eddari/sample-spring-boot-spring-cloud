package fr.red.services.domain.service;

import fr.red.services.domain.model.ExpenseReport;
import fr.red.services.domain.port.application.ExpenseReportRequester;
import fr.red.services.domain.port.infrastructure.ExpenseReportDataGateway;

import java.util.List;

public class ExpenseReportService implements ExpenseReportRequester {

    private final ExpenseReportDataGateway expenseReportDataGateway;

    public ExpenseReportService(ExpenseReportDataGateway expenseReportDataGateway) {
        this.expenseReportDataGateway = expenseReportDataGateway;
    }

    @Override
    public List<ExpenseReport> getExpenseReportList() {
        return expenseReportDataGateway.getExpenseReportList();
    }

    @Override
    public List<ExpenseReport> getExpenseReportListByEmpno(String empno) {
        return expenseReportDataGateway.getExpenseReportListByEmpno(empno);
    }

    @Override
    public ExpenseReport getExpenseReportById(String id) {
        return expenseReportDataGateway.getExpenseReportById(id);
    }

    @Override
    public ExpenseReport addExpenseReport(ExpenseReport expenseReport) {
        return expenseReportDataGateway.addExpenseReport(expenseReport);
    }

    @Override
    public ExpenseReport updateExpenseReport(ExpenseReport expenseReport) {
        return expenseReportDataGateway.updateExpenseReport(expenseReport);
    }

    @Override
    public void deleteExpenseReport(ExpenseReport expenseReport) {
        expenseReportDataGateway.deleteExpenseReport(expenseReport);
    }
}
