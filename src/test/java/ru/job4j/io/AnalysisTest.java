package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    300 10:59:01
                    500 11:01:02
                    200 11:02:02""");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }

        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").hasToString(result.toString());
    }

    @Test
    public void testUnavailable() throws IOException {
        Path tempDir = Files.createTempDirectory("tempDir");
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("""
                    200 10:56:01
                    300 10:57:01
                    200 10:58:01
                    300 10:59:01""");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }

        assertEquals("", result.toString());
    }
}
