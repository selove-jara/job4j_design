package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairNull() {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#hibernate.connection.username"), is(Matchers.nullValue()));
    }

    @Test
    public void whenMultiplePairDivider() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"), is("org=postgresql=Driver"));
    }

    @Test
    public void whenPairWithException() {
        thrown.expect(IllegalArgumentException.class);
        String path = "./data/exception.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairException() {
        thrown.expect(IllegalArgumentException.class);
        String path = "./data/exception_value.properties";
        Config config = new Config(path);
        config.load();
    }
}