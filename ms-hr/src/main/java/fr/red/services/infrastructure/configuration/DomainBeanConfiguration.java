package fr.red.services.infrastructure.configuration;

import fr.red.services.domain.department.DepartmentService;
import fr.red.services.domain.department.port.infrastructure.DepartmentDataGateway;
import fr.red.services.domain.employe.port.infrastructure.EmployeDataGateway;
import fr.red.services.domain.employe.service.EmployeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfiguration {

    @Bean
    DepartmentService departmentService(DepartmentDataGateway departmentDataGateway){
        return new DepartmentService(departmentDataGateway);
    }

    @Bean
    EmployeService employeService(EmployeDataGateway employeDataGateway){
        return new EmployeService(employeDataGateway);
    }

}
