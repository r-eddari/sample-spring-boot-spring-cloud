package fr.red.services.infrastructure.adapter;

import fr.red.services.domain.model.ExpenseReport;
import fr.red.services.domain.port.infrastructure.ExpenseReportDataGateway;
import fr.red.services.infrastructure.mapper.ExpenseReportMapper;
import fr.red.services.infrastructure.model.RefExpenseReport;
import fr.red.services.infrastructure.repository.ExpenseReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExpenseReportAdapter implements ExpenseReportDataGateway {

    @Autowired
    ExpenseReportRepository repository;

    @Override
    public List<ExpenseReport> getExpenseReportList() {

        List<RefExpenseReport> expenseReportList = repository.findAll();

        return ExpenseReportMapper.mappingRefExpenseReportToExpenseReport(expenseReportList);
    }

    @Override
    public List<ExpenseReport> getExpenseReportListByEmpno(String empno) {

        List<RefExpenseReport> expenseReportList = repository.findByEmploye(empno);

        return ExpenseReportMapper.mappingRefExpenseReportToExpenseReport(expenseReportList);
    }

    @Override
    public ExpenseReport getExpenseReportById(String id) {

        ExpenseReport expenseReport = ExpenseReport.builder().build();

        Optional<RefExpenseReport> optionalRefExpenseReport = repository.findById(id);

        if (optionalRefExpenseReport.isPresent())
                expenseReport =  ExpenseReportMapper.mappingRefExpenseReportToExpenseReport(optionalRefExpenseReport.get());

        return expenseReport;
    }

    @Override
    public ExpenseReport addExpenseReport(ExpenseReport expenseReport) {

        RefExpenseReport expenseReportToAdd = ExpenseReportMapper.mappingExpenseReportToRefExpenseReport(expenseReport);

        return  ExpenseReportMapper.mappingRefExpenseReportToExpenseReport(repository.saveOrUpdate(expenseReportToAdd));
    }

    @Override
    public ExpenseReport updateExpenseReport(ExpenseReport expenseReport) {

        RefExpenseReport expenseReportToUpdate = ExpenseReportMapper.mappingExpenseReportToRefExpenseReport(expenseReport);

        return  ExpenseReportMapper.mappingRefExpenseReportToExpenseReport(repository.saveOrUpdate(expenseReportToUpdate));
    }

    @Override
    public void deleteExpenseReport(ExpenseReport expenseReport) {

        RefExpenseReport expenseReportToDelete = ExpenseReportMapper.mappingExpenseReportToRefExpenseReport(expenseReport);

        repository.delete(expenseReportToDelete);

    }
}
