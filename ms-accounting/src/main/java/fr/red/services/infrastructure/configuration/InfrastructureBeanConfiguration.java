package fr.red.services.infrastructure.configuration;

import fr.red.services.infrastructure.model.RefExpenseReport;
import fr.red.services.infrastructure.repository.ExpenseReportRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
public class InfrastructureBeanConfiguration {

    @Bean
    ExpenseReportRepository expenseReportRepository() throws ParseException {
        ExpenseReportRepository expenseReportRepository = new ExpenseReportRepository();
        expenseReportRepository.saveOrUpdate(new RefExpenseReport("1", "CATERING", toDate("03-01-2020"), 40d, true, "7499"));
        expenseReportRepository.saveOrUpdate(new RefExpenseReport("2", "HOTEL", toDate("03-01-2020"), 120d, true, "7499"));
        expenseReportRepository.saveOrUpdate(new RefExpenseReport("3", "TAXI", toDate("03-01-2020"), 70d, true, "7499"));
        expenseReportRepository.saveOrUpdate(new RefExpenseReport("4", "HOTEL", toDate("16-03-2020"), 140d, false, "7566"));
        expenseReportRepository.saveOrUpdate(new RefExpenseReport("5", "TAXI", toDate("16-01-2020"), 35d, false, "7566"));
        return expenseReportRepository;
    }

    private Date toDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return formatter.parse(dateString);
    }
}
