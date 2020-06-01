package fr.red.services.proxy;

import fr.red.services.model.department.Department;
import fr.red.services.model.employe.Employe;
import fr.red.services.model.expensereport.ExpenseReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "api-gateway")
public interface MsHrProxy {

    @GetMapping("/employes")
    List<Employe> getEmployes();

    @GetMapping("/employes/{empno}")
    Employe getEmployeById(@PathVariable("empno") String empno);

    @PostMapping("/employes")
    Employe addEmploye(@RequestBody Employe employe);

    @PutMapping("/employes")
    Employe updateEmploye(@RequestBody Employe employe);

    @DeleteMapping("/employes/{empno}")
    Map<String, String> deleteEmploye(@PathVariable("empno") String empno);

    @GetMapping("/departments")
    List<Department> getDepartments();

    @GetMapping("/departments/{deptno}")
    Department getDepartmentById(@PathVariable("deptno") String deptno);

    @PostMapping("/departments")
    Department addDepartment(@RequestBody Department department);

    @PostMapping("/departments")
    Department updateDepartment(@RequestBody Department department);

    @DeleteMapping("/departments")
    Map<String, String> deleteDepartmentWithItsEmployes(@RequestBody Department department);

    @GetMapping("/expensereports")
    List<ExpenseReport> getExpenseReportList();

    @GetMapping("/expensereports/employes/{empno}")
    List<ExpenseReport> getExpenseReportListByEmpno(@PathVariable("empno") String empno);

    @GetMapping("/expensereports/{id}")
    ExpenseReport getExpenseReportById(@PathVariable("id") String id);

    @PostMapping("/expensereports")
    ExpenseReport addExpenseReport(@RequestBody ExpenseReport expenseReport);

    @PutMapping("/expensereports")
    ExpenseReport updateExpenseReport(@RequestBody ExpenseReport expenseReport);

    @DeleteMapping("/expensereports/{id}")
    Map<String, String> deleteExpenseReport(@PathVariable("id") String id);
}
