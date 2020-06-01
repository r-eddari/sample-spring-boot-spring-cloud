package fr.red.services.infrastructure.department.referentiel.adapter;

import fr.red.services.domain.department.model.Department;
import fr.red.services.domain.department.port.infrastructure.DepartmentDataGateway;
import fr.red.services.infrastructure.department.referentiel.mapper.DepartmentMapper;
import fr.red.services.infrastructure.department.referentiel.model.RefDepartment;
import fr.red.services.infrastructure.department.referentiel.repository.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentAdapter implements DepartmentDataGateway {

    private final DepartmentRepository departmentRepository;

    public DepartmentAdapter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getDepartments() {

        List<RefDepartment> refDepartments = departmentRepository.findAll();

        return DepartmentMapper.mapRefDepartmentsToDepartments(refDepartments);
    }

    @Override
    public Department getDepartmentById(String deptno) {

        Department result = Department.builder().build();

        Optional<RefDepartment> optionalRefDepartment = departmentRepository.findById(deptno);

        if(optionalRefDepartment.isPresent())
            result = DepartmentMapper.mapRefDepartmentToDepartment(optionalRefDepartment.get());

        return result;
    }

    @Override
    public Department addDepartment(Department department) {

        RefDepartment refDepartment = DepartmentMapper.mapDepartmentToRefDepartment(department);

        RefDepartment addedDepartment = departmentRepository.saveOrUpdate(refDepartment);

        Department result = DepartmentMapper.mapRefDepartmentToDepartment(addedDepartment);

        return result;
    }

    @Override
    public Department updateDepartment(Department department) {

        RefDepartment refDepartment = DepartmentMapper.mapDepartmentToRefDepartment(department);

        RefDepartment addedDepartment = departmentRepository.saveOrUpdate(refDepartment);

        Department result = DepartmentMapper.mapRefDepartmentToDepartment(addedDepartment);

        return result;
    }

    @Override
    public void deleteDepartmentWithItsEmployes(Department department) {

        RefDepartment refDepartment = DepartmentMapper.mapDepartmentToRefDepartment(department);

        departmentRepository.deleteDepartmentWithItsEmployes(refDepartment);
    }

}
