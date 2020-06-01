package fr.red.services.rest;

import fr.red.services.model.department.Department;
import fr.red.services.model.employe.Employe;
import fr.red.services.model.expensereport.ExpenseReport;
import fr.red.services.proxy.MsHrProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClientResource {

    @Autowired
    private MsHrProxy msHrProxy;

    @GetMapping("/employes")
    public ResponseEntity<List<Employe>> getEmployes(){
        return ResponseEntity.ok().body(msHrProxy.getEmployes());
    }

    @GetMapping("/employes/{empno}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable String empno){
        return ResponseEntity.ok().body(msHrProxy.getEmployeById(empno));
    }

    @PostMapping("/employes")
    public ResponseEntity<Employe> addEmploye(@RequestBody Employe employe){
        return ResponseEntity.ok().body(msHrProxy.addEmploye(employe));
    }

    @PutMapping("/employes")
    public ResponseEntity<Employe> updateEmploye(@RequestBody Employe employe){
        return ResponseEntity.ok().body(msHrProxy.addEmploye(employe));
    }

    @DeleteMapping("/employes/{employe}")
    public Map<String, String> deleteEmploye(@PathVariable String employe){
        return msHrProxy.deleteEmploye(employe);
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<List<Department>> getDepartments(){
        return ResponseEntity.ok().body(msHrProxy.getDepartments());
    }

    @GetMapping("/departments/{deptno}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("deptno") String deptno){
        return ResponseEntity.ok().body(msHrProxy.getDepartmentById(deptno));
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return ResponseEntity.ok().body(msHrProxy.addDepartment(department));
    }

    @PutMapping("/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        return ResponseEntity.ok().body(msHrProxy.updateDepartment(department));
    }

    @DeleteMapping("/departments")
    public Map<String, String> deleteDepartmentWithItsEmployes(@RequestBody Department department){
       return msHrProxy.deleteDepartmentWithItsEmployes(department);
    }

    @GetMapping("/expensereports")
    public ResponseEntity<List<ExpenseReport>> getExpenseReportList(){
        return ResponseEntity.ok().body(msHrProxy.getExpenseReportList());
    }

    @GetMapping("/expensereports/employes/{empno}")
    public ResponseEntity<List<ExpenseReport>> getExpenseReportListByEmpno(@PathVariable("empno") String empno){
        return ResponseEntity.ok().body(msHrProxy.getExpenseReportListByEmpno(empno));
    }

    @GetMapping("/expensereports/{id}")
    public ResponseEntity<ExpenseReport> getExpenseReportById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(msHrProxy.getExpenseReportById(id));
    }

    @PostMapping("/expensereports")
    public ResponseEntity<ExpenseReport> addExpenseReport(@RequestBody ExpenseReport expenseReport){
        return ResponseEntity.ok().body(msHrProxy.addExpenseReport(expenseReport));
    }

    @PutMapping("/expensereports")
    public ResponseEntity<ExpenseReport> updateExpenseReport(@RequestBody ExpenseReport expenseReport){
        return ResponseEntity.ok().body(msHrProxy.updateExpenseReport(expenseReport));
    }

    @DeleteMapping("/expensereports/{id}")
    public Map<String, String> deleteExpenseReport(@PathVariable("id") String id){
        return msHrProxy.deleteExpenseReport(id);
    }

}
