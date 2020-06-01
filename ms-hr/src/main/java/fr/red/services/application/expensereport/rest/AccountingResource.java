package fr.red.services.application.expensereport.rest;

import fr.red.services.application.expensereport.model.ExpenseReport;
import fr.red.services.application.expensereport.proxy.MsAccountingProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/private/api/v1/accounting")
public class AccountingResource {

    @Autowired
    MsAccountingProxy accountingProxy;

    @GetMapping
    public ResponseEntity<List<ExpenseReport>> getExpenseReportList(){
        return ResponseEntity.ok().body(accountingProxy.getExpenseReportList());
    }

    @GetMapping("/employes/{empno}")
    public ResponseEntity<List<ExpenseReport>> getExpenseReportListByEmpno(@PathVariable String empno){
        List<ExpenseReport> expenseReportListByEmpno = accountingProxy.getExpenseReportListByEmpno(empno);
        return ResponseEntity.ok().body(expenseReportListByEmpno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseReport> getExpenseReportById(@PathVariable String id){
        return ResponseEntity.ok().body(accountingProxy.getExpenseReportById(id));
    }

    @PostMapping
    public ResponseEntity<ExpenseReport> addExpenseReport(@RequestBody ExpenseReport expensereport){
        return ResponseEntity.ok().body(accountingProxy.addExpenseReport(expensereport));
    }

    @PutMapping
    public ResponseEntity<ExpenseReport> updateExpenseReport(@RequestBody ExpenseReport expensereport){
        return ResponseEntity.ok().body(accountingProxy.updateExpenseReport(expensereport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteExpenseReport(@PathVariable String id){
        return ResponseEntity.ok().body(accountingProxy.deleteExpenseReport(id));
    }

}
