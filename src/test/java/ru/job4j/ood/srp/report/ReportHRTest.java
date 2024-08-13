package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportHRTest {

    @Test
    public void whenReportSortedBySalaryDesc() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Vasek", now, now, 3000);
        Employee emp2 = new Employee("Andrey", now, now, 7000);
        Employee emp3 = new Employee("Alisa", now, now, 5000);
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);

        Report report = new ReportHR(store);

        String expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Andrey 7000.0")
                .append(System.lineSeparator())
                .append("Alisa 5000.0")
                .append(System.lineSeparator())
                .append("Vasek 3000.0")
                .append(System.lineSeparator())
                .toString();

        assertThat(report.generate(emp -> true)).isEqualTo(expected);
    }

}