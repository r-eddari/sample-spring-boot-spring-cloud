package fr.red.services.infrastructure.department.referentiel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefDepartment {

    private String deptno;
    private String dname;
    private String loc;
}
