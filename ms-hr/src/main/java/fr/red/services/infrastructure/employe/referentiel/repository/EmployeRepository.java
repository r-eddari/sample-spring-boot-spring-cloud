package fr.red.services.infrastructure.employe.referentiel.repository;

import fr.red.services.infrastructure.employe.referentiel.model.RefEmploye;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeRepository {

    private List<RefEmploye> employes = new ArrayList<>();

    public RefEmploye saveOrUpdate(RefEmploye refEmploye) {

        RefEmploye refEmp = findById(refEmploye.getEmpno()).orElse(null);
        if(refEmp != null){
            int index = employes.indexOf(refEmp);
            employes.set(index, refEmploye);
        }
        else
            employes.add(refEmploye);
        return refEmploye;
    }

    public Optional<RefEmploye> findById(String empno) {
        return employes.stream().filter(refEmploye -> refEmploye.getEmpno().equals(empno)).findFirst();
    }

    public List<RefEmploye> findAll() {
        return employes;
    }

    public void delete(RefEmploye refEmploye){
        employes.remove(refEmploye);
    }

}
