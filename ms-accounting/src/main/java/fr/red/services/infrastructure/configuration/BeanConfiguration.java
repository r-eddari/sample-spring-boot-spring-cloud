package fr.red.services.infrastructure.configuration;

import fr.red.services.domain.port.infrastructure.ExpenseReportDataGateway;
import fr.red.services.domain.service.ExpenseReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ExpenseReportService ExpenseReportService(ExpenseReportDataGateway expenseReportDataGateway){
        return new ExpenseReportService(expenseReportDataGateway);
    }
}
