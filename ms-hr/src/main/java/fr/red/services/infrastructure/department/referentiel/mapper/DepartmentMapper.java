package fr.red.services.infrastructure.department.referentiel.mapper;

import fr.red.services.domain.department.model.Department;
import fr.red.services.infrastructure.department.referentiel.model.RefDepartment;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static Department mapRefDepartmentToDepartment(RefDepartment refDepartment){
        return Department.builder()
                         .deptno(refDepartment.getDeptno())
                         .dname(refDepartment.getDname())
                         .loc(refDepartment.getLoc())
                         .build();
    }

    public static RefDepartment mapDepartmentToRefDepartment(Department department){
        return RefDepartment.builder()
                            .deptno(department.getDeptno())
                            .dname(department.getDname())
                            .loc(department.getLoc())
                            .build();
    }

    public static List<Department> mapRefDepartmentsToDepartments(List<RefDepartment> refEmployes){
        return refEmployes.stream().map(refDepartment -> mapRefDepartmentToDepartment(refDepartment)).collect(
                Collectors.toList());
    }
}