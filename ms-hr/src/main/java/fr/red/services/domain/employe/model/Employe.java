package fr.red.services.domain.employe.model;

import fr.red.services.domain.department.model.Department;

import java.util.Date;

public class Employe {

    private String empno;
    private String ename;
    private String job;
    private Date hiredate;
    private Double salary;
    private Department department;


    public Employe(String empno, String ename, String job, Date hiredate, Double salary, Department department) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.hiredate = hiredate;
        this.salary = salary;
        this.department = department;
    }

    public String getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public Double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public static EmployeBuilder builder() {
        return new EmployeBuilder();
    }

    public static class EmployeBuilder {

        private String empno;
        private String ename;
        private String job;
        private Date hiredate;
        private Double salary;
        private Department department;

        EmployeBuilder() {
        }

        public EmployeBuilder empno(String empno) {
            this.empno = empno;
            return this;
        }

        public EmployeBuilder ename(String ename) {
            this.ename = ename;
            return this;
        }

        public EmployeBuilder job(String job) {
            this.job = job;
            return this;
        }

        public EmployeBuilder hiredate(Date hiredate) {
            this.hiredate = hiredate;
            return this;
        }

        public EmployeBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public EmployeBuilder department(Department department) {
            this.department = department;
            return this;
        }

        public Employe build() {
            return new Employe(empno, ename, job, hiredate, salary, department);
        }
    }
}
