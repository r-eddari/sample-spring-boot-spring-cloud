package fr.red.services.infrastructure.repository;

import fr.red.services.infrastructure.model.RefExpenseReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpenseReportRepository {

    private List<RefExpenseReport> refExpenseReports = new ArrayList<>();

    public List<RefExpenseReport> findAll() {
        return refExpenseReports;
    }

    public Optional<RefExpenseReport> findById(String id) {
        return refExpenseReports.stream().filter(refExpenseReport -> refExpenseReport.getId().equals(id)).findFirst();
    }

    public RefExpenseReport saveOrUpdate(RefExpenseReport refExpenseReport) {
        RefExpenseReport expenseReport = findById(refExpenseReport.getId()).orElse(null);
        if(expenseReport != null){
            int index = refExpenseReports.indexOf(expenseReport);
            refExpenseReports.set(index, refExpenseReport);
        }else
            refExpenseReports.add(refExpenseReport);

        return refExpenseReport;
    }

    public void delete(RefExpenseReport refExpenseReport) {
        refExpenseReports.remove(refExpenseReport);
    }

    public List<RefExpenseReport> findByEmploye(String empno) {
        return refExpenseReports.stream().filter(refExpenseReport -> refExpenseReport.getEmpno().equals(empno)).collect(
                Collectors.toList());
    }
}
