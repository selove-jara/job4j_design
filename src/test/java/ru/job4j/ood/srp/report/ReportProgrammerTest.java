package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReportProgrammerTest {
    @Test
    void whenGenerateReport() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report report = new ReportProgrammer(store, parser);
        Employee emp1 = new Employee("Vasek", now, now, 3000);
        Employee emp2 = new Employee("Andrey", now, now, 7000);
        Employee emp3 = new Employee("Alisa", now, now, 5000);
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);
        String expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append("Vasek, ").append(parser.parse(now)).append(", ")
                .append(parser.parse(now)).append(", 3000.0").append(System.lineSeparator())
                .append("Andrey, ").append(parser.parse(now)).append(", ")
                .append(parser.parse(now)).append(", 7000.0").append(System.lineSeparator())
                .append("Alisa, ").append(parser.parse(now)).append(", ")
                .append(parser.parse(now)).append(", 5000.0").append(System.lineSeparator())
                .toString();
        assertThat(report.generate(employee -> true)).isEqualTo(expected);
    }

}