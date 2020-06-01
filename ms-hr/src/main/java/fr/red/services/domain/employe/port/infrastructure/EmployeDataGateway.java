package fr.red.services.domain.employe.port.infrastructure;

import fr.red.services.domain.employe.model.Employe;

import java.util.List;

public interface EmployeDataGateway {

    List<Employe> getEmployes();

    Employe getEmployeById(String idEmploye);

    Employe addEmploye(Employe employe);

    Employe updateEmploye(Employe employe);

    void deleteEmploye(Employe employe);
}
