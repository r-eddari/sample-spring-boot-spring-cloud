package fr.red.services.application.department;

import fr.red.services.application.department.mapper.DepartmentMapper;
import fr.red.services.application.department.model.DepartmentUI;
import fr.red.services.domain.department.model.Department;
import fr.red.services.domain.department.port.application.DepartmentRequester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/private/api/v1/departments")
public class DepartmentResource {


    private final DepartmentRequester departmentRequester;

    public DepartmentResource(DepartmentRequester departmentRequester) {
        this.departmentRequester = departmentRequester;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentUI>> getDepartments(){
        List<Department> departments = departmentRequester.getDepartments();

        List<DepartmentUI> result = DepartmentMapper.mapDepartmentsToDepartmentsUI(departments);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentUI> getDepartmentById(@PathVariable String id){

        Department department = departmentRequester.getDepartmentById(id);

        DepartmentUI result = DepartmentMapper.mapDepartmentToDepartmentUI(department);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<DepartmentUI> addDepartment(@RequestBody DepartmentUI departmentUI){

        Department department = DepartmentMapper.mapDepartmentUIToDepartment(departmentUI);

        Department dept = departmentRequester.addDepartment(department);

        DepartmentUI result = DepartmentMapper.mapDepartmentToDepartmentUI(dept);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DepartmentUI> updateDepartment(@RequestBody DepartmentUI departmentUI){

        Department department = DepartmentMapper.mapDepartmentUIToDepartment(departmentUI);

        Department dept = departmentRequester.updateDepartment(department);

        DepartmentUI result = DepartmentMapper.mapDepartmentToDepartmentUI(dept);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteDepartmentWithItsEmployes(@PathVariable String id){

        Department department = departmentRequester.getDepartmentById(id);

        departmentRequester.deleteDepartmentWithItsEmployes(department);

        Map<String, String> result = new HashMap<>();
        result.put("Deleting department with its employes!", "success");

        return ResponseEntity.ok().body(result);
    }

}
