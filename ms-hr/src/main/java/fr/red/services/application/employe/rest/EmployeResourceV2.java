package fr.red.services.application.employe.rest;

import fr.red.services.application.employe.mapper.EmployeMapper;
import fr.red.services.application.employe.model.EmployeUI;
import fr.red.services.domain.employe.model.Employe;
import fr.red.services.domain.employe.port.application.EmployeRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/private/api/v2/employes")
public class EmployeResourceV2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeResourceV2.class);

    private final EmployeRequester employeRequester;

    public EmployeResourceV2(EmployeRequester employeRequester) {
        this.employeRequester = employeRequester;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeUI>> getEmployes(){

        LOGGER.info("getEmployes v2");

        List<Employe> employes = employeRequester.getEmployes();

        List<EmployeUI> result = EmployeMapper.mapEmployesToEmployesUI(employes);

        return ResponseEntity.ok().body(result);
    }
}
