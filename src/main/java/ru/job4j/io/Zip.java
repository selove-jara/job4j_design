package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("You have no arguments loaded");
        }
        ArgsName argsName = ArgsName.of(args);
        Path path = Paths.get(argsName.get("d"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("Source doesn't exist");
        }
        if (!argsName.get("o").contains(".")) {
            throw new IllegalArgumentException("You have to add filename to write the data");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Incorrect start parameter");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        Path directory = Path.of(argsName.get("d"));
        Predicate<Path> pred = (p) -> !p.toFile().getName().endsWith(argsName.get("e"));
        List<Path> list = Search.search(directory, pred);
        Path target = Path.of(argsName.get("o"));
        zip.packFiles(list, target.toFile());
    }
}