package fr.red.services.infrastructure.department.referentiel.repository;

import fr.red.services.infrastructure.department.referentiel.model.RefDepartment;
import fr.red.services.infrastructure.employe.referentiel.repository.EmployeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepository {

    private final EmployeRepository employeRepository;

    private List<RefDepartment> departments = new ArrayList<>();

    public DepartmentRepository(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public RefDepartment saveOrUpdate(RefDepartment department) {
        RefDepartment refDepartment = findById(department.getDeptno()).orElse(null);
        if(refDepartment != null){
            int index = departments.indexOf(refDepartment);
            departments.set(index, department);
        }else
            departments.add(department);
        return department;
    }

    public Optional<RefDepartment> findById(String deptno) {
        return departments.stream().filter(refDepartment -> refDepartment.getDeptno().equals(deptno)).findFirst();
    }

    public List<RefDepartment> findAll() {
        return departments;
    }

    public void deleteDepartmentWithItsEmployes(RefDepartment refDepartment){
        employeRepository.findAll().stream().filter(refEmploye -> refEmploye.getDeptno().equals(refDepartment.getDeptno())).forEach(refEmploye -> {
            employeRepository.delete(refEmploye);
        });

        departments.remove(refDepartment);
    }
}
