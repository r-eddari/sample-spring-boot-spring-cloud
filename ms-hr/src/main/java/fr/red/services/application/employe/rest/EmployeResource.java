package fr.red.services.application.employe.rest;

import fr.red.services.application.employe.mapper.EmployeMapper;
import fr.red.services.application.employe.model.EmployeUI;
import fr.red.services.domain.employe.model.Employe;
import fr.red.services.domain.employe.port.application.EmployeRequester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/private/api/v1/employes")
public class EmployeResource {

    private final EmployeRequester employeRequester;

    public EmployeResource(EmployeRequester employeRequester) {
        this.employeRequester = employeRequester;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeUI>> getEmployes(){
        List<Employe> employes = employeRequester.getEmployes();

        List<EmployeUI> result = EmployeMapper.mapEmployesToEmployesUI(employes);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeUI> getEmployes(@PathVariable String id){

        Employe employe = employeRequester.getEmployeById(id);

        EmployeUI result = EmployeMapper.mapEmployeToEmployeUI(employe);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public ResponseEntity<EmployeUI> addEmploye(@RequestBody EmployeUI employeUI){

        Employe employe = EmployeMapper.mapEmployeUIToEmploye(employeUI);

        Employe emp = employeRequester.addEmploye(employe);

        EmployeUI result = EmployeMapper.mapEmployeToEmployeUI(emp);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<EmployeUI> updateEmploye(@RequestBody EmployeUI employeUI){

        Employe employe = EmployeMapper.mapEmployeUIToEmploye(employeUI);

        Employe emp = employeRequester.updateEmploye(employe);

        EmployeUI result = EmployeMapper.mapEmployeToEmployeUI(emp);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteEmploye(@PathVariable String id){

        Employe employe = employeRequester.getEmployeById(id);

        employeRequester.deleteEmploye(employe);

        Map<String, String> result = new HashMap<>();
        result.put("Deleting employe", "success");

        return ResponseEntity.ok().body(result);
    }
}
