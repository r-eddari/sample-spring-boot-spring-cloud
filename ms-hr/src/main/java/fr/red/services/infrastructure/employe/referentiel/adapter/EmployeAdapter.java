package fr.red.services.infrastructure.employe.referentiel.adapter;

import fr.red.services.domain.employe.model.Employe;
import fr.red.services.domain.employe.port.infrastructure.EmployeDataGateway;
import fr.red.services.infrastructure.employe.referentiel.mapper.EmployeMapper;
import fr.red.services.infrastructure.employe.referentiel.model.RefEmploye;
import fr.red.services.infrastructure.employe.referentiel.repository.EmployeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeAdapter implements EmployeDataGateway {

    private final EmployeRepository repository;

    public EmployeAdapter(EmployeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employe> getEmployes() {

        List<RefEmploye> refEmployes = repository.findAll();

        return EmployeMapper.mapRefEmployesToEmployes(refEmployes);
    }

    @Override
    public Employe getEmployeById(String idEmploye) {

        Employe result = Employe.builder().build();

        Optional<RefEmploye> optionalRefEmploye = repository.findById(idEmploye);

        if(optionalRefEmploye.isPresent())
            result = EmployeMapper.mapRefEmployeToEmploye(optionalRefEmploye.get());

        return result;
    }

    @Override
    public Employe addEmploye(Employe employe) {

        RefEmploye refEmploye = EmployeMapper.mapEmployeToRefEmploye(employe);

        RefEmploye addedEmploye = repository.saveOrUpdate(refEmploye);

        Employe result = EmployeMapper.mapRefEmployeToEmploye(addedEmploye);

        return result;
    }

    @Override
    public Employe updateEmploye(Employe employe) {

        RefEmploye refEmploye = EmployeMapper.mapEmployeToRefEmploye(employe);

        RefEmploye updatedEmploye = repository.saveOrUpdate(refEmploye);

        Employe result = EmployeMapper.mapRefEmployeToEmploye(updatedEmploye);

        return result;
    }

    @Override
    public void deleteEmploye(Employe employe) {

        RefEmploye refEmploye = EmployeMapper.mapEmployeToRefEmploye(employe);

        repository.delete(refEmploye);
    }
}
