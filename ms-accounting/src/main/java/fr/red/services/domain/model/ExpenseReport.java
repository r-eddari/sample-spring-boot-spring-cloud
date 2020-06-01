package fr.red.services.domain.model;

import java.util.Date;

public class ExpenseReport {
    private String id;
    private ExpenseTypeEnum type;
    private Date creationDate;
    private Double amount;
    private boolean refunded;
    private String empno;

    public ExpenseReport(String id, ExpenseTypeEnum type, Date creationDate, Double amount, boolean refunded, String empno) {
        this.id = id;
        this.type = type;
        this.creationDate = creationDate;
        this.amount = amount;
        this.refunded = refunded;
        this.empno = empno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExpenseTypeEnum getType() {
        return type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Double getAmount() {
        return amount;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public String getEmpno() {
        return empno;
    }

    public static ExpenseReportBuilder builder() {
        return new ExpenseReportBuilder();
    }

    public static class ExpenseReportBuilder {

        private String id;
        private ExpenseTypeEnum type;
        private Date creationDate;
        private Double amount;
        private boolean refunded;
        private String empno;

        ExpenseReportBuilder() {
        }

        public ExpenseReportBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ExpenseReportBuilder type(ExpenseTypeEnum type) {
            this.type = type;
            return this;
        }

        public ExpenseReportBuilder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public ExpenseReportBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public ExpenseReportBuilder refunded(boolean refunded) {
            this.refunded = refunded;
            return this;
        }

        public ExpenseReportBuilder employe(String empno) {
            this.empno = empno;
            return this;
        }

        public ExpenseReport build() {
            return new ExpenseReport(id, type, creationDate, amount, refunded, empno);
        }

        public String toString() {
            return "ExpenseReport.ExpenseReportBuilder(id=" + this.id + ", type=" + this.type + ", creationDate=" +
                    this.creationDate + ", amount=" + this.amount + ", refunded=" + this.refunded + ", empno=" +
                    this.empno + ")";
        }
    }
}
