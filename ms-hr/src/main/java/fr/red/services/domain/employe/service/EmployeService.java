package fr.red.services.domain.employe.service;

import fr.red.services.domain.employe.model.Employe;
import fr.red.services.domain.employe.port.application.EmployeRequester;
import fr.red.services.domain.employe.port.infrastructure.EmployeDataGateway;

import java.util.List;

public class EmployeService implements EmployeRequester {

    private final EmployeDataGateway employeDataGateway;

    public EmployeService(EmployeDataGateway employeDataGateway) {
        this.employeDataGateway = employeDataGateway;
    }

    @Override
    public List<Employe> getEmployes() {
        return employeDataGateway.getEmployes();
    }

    @Override
    public Employe getEmployeById(String idEmploye) {
        return employeDataGateway.getEmployeById(idEmploye);
    }

    @Override
    public Employe addEmploye(Employe employe) {
        return employeDataGateway.addEmploye(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        return employeDataGateway.updateEmploye(employe);
    }

    @Override
    public void deleteEmploye(Employe employe) {
        employeDataGateway.deleteEmploye(employe);
    }
}
