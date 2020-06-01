package fr.red.services.application.rest;

import fr.red.services.application.mapper.ExpenseReportMapper;
import fr.red.services.application.model.ExpenseReportUI;
import fr.red.services.domain.model.ExpenseReport;
import fr.red.services.domain.port.application.ExpenseReportRequester;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/private/api/v1/expensereports")
public class ExpenseReportResource {

    private final ExpenseReportRequester expenseReportRequester;

    public ExpenseReportResource(ExpenseReportRequester expenseReportRequester) {
        this.expenseReportRequester = expenseReportRequester;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseReportUI>> getExpenseReportList(){
        List<ExpenseReport> ExpenseReportList = expenseReportRequester.getExpenseReportList();

        List<ExpenseReportUI> expenseReportUIs = ExpenseReportMapper.mappingExpenseReportToExpenseReportUI(ExpenseReportList);

        return ResponseEntity.ok().body(expenseReportUIs);
    }

    @GetMapping(path = "/employes/{empno}")
    public ResponseEntity<List<ExpenseReportUI>> getExpenseReportListByEmpno(@PathVariable String empno){
        List<ExpenseReport> expenseReportListByEmpno = expenseReportRequester.getExpenseReportListByEmpno(empno);
        List<ExpenseReportUI> expenseReportUIS = ExpenseReportMapper.mappingExpenseReportToExpenseReportUI(
                expenseReportListByEmpno);
        return ResponseEntity.ok().body(expenseReportUIS);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ExpenseReportUI> getExpenseReportById(@PathVariable String id){
        ExpenseReport expenseReportById = expenseReportRequester.getExpenseReportById(id);
        ExpenseReportUI result = ExpenseReportMapper.mappingExpenseReportToExpenseReportUI(expenseReportById);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<ExpenseReport> addExpenseReport(@RequestBody ExpenseReportUI expenseReportUI){
        ExpenseReport expenseReport = ExpenseReportMapper.mappingExpenseReportUIToExpenseReport(expenseReportUI);
        ExpenseReport result = expenseReportRequester.addExpenseReport(expenseReport);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public ResponseEntity<ExpenseReport> updateExpenseReport(@RequestBody ExpenseReportUI expenseReportUI){
        ExpenseReport expenseReport = ExpenseReportMapper.mappingExpenseReportUIToExpenseReport(expenseReportUI);
        ExpenseReport result = expenseReportRequester.updateExpenseReport(expenseReport);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteExpenseReport(@PathVariable String id){

        ExpenseReport expenseReport = expenseReportRequester.getExpenseReportById(id);
        expenseReportRequester.deleteExpenseReport(expenseReport);

        Map<String, String> result = new HashMap<>();
        result.put("Deleting expenseReport", "success");
        return ResponseEntity.ok().body(result);
    }
}
