package fr.red.services.application.expensereport.proxy;

import fr.red.services.application.expensereport.model.ExpenseReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-accounting")
@RequestMapping("/private/api/v1/expensereports")
public interface MsAccountingProxy {

    @GetMapping("/employes/{empno}")
    List<ExpenseReport> getExpenseReportListByEmpno(@PathVariable("empno") String empno);

    @GetMapping
    List<ExpenseReport> getExpenseReportList();

    @GetMapping("/{id}")
    ExpenseReport getExpenseReportById(@PathVariable("id") String id);

    @PostMapping
    ExpenseReport addExpenseReport(@RequestBody ExpenseReport expenseReport);

    @PutMapping
    ExpenseReport updateExpenseReport(@RequestBody ExpenseReport expenseReport);

    @DeleteMapping("/{id}")
    Map<String, String> deleteExpenseReport(@PathVariable("id") String id);
}
