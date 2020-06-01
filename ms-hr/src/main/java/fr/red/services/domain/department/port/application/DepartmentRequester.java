package fr.red.services.domain.department.port.application;

import fr.red.services.domain.department.model.Department;

import java.util.List;

public interface DepartmentRequester {

    List<Department> getDepartments();

    Department getDepartmentById(String deptno);

    Department addDepartment(Department department);

    Department updateDepartment(Department department);

    void deleteDepartmentWithItsEmployes(Department department);
}
