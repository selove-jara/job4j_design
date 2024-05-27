package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        map.computeIfAbsent(fileProperty, f -> new ArrayList<>());
        map.get(fileProperty).add(file.toAbsolutePath());
        map.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(System.out::println);
        return FileVisitResult.CONTINUE;
    }
}