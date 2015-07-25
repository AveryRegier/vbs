package com.github.averyregier.vbs.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.stream.Stream;

import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.joining;

/**
 * Created by avery on 7/17/15.
 */
public class VBSFormToCSV {

    public static void main(String... args) throws IOException {
        String csvPath = args.length > 1 ? args[1] : "./vbs.csv";
        String location = args.length > 0 ? args[0] : ".";
        new VBSFormToCSV().writeToCSV(location, csvPath);
    }

    private void writeToCSV(String location, String csvPath) throws IOException {
        Path csv = Paths.get(csvPath);
        try (PrintWriter writer = openCSV(csv)) {
            Path path = Paths.get(location);
            convertToCSV(path).forEach(writer::println);
        }
    }

    private PrintWriter openCSV(Path csv) throws IOException {
        PrintWriter writer;
        try {
            File file = Files.createFile(csv).toFile();
            writer = getPrintWriter(file);
            writer.println(createHeader());
        } catch (FileAlreadyExistsException e) {
            // no bother
            writer = getPrintWriter(csv.toFile());
            writer.println(); // make sure we start on a new line
        }
        return writer;
    }

    private Stream<String> convertToCSV(Path path) throws IOException {
        if(isDirectory(path)) {
            return parseAll(path);
        } else {
            return parseOne(path);
        }
    }

    private PrintWriter getPrintWriter(File file) throws IOException {
        return new PrintWriter(new FileWriter(file, true));
    }

    private String createHeader() {
        return toCSV(
                "Given",
                "Surname",
                "Age",
                "Grade",
                "Note",
                "Street",
                "Street2",
                "City",
                "State",
                "Region",
                "Country",
                "Parent Given Name",
                "Parent Surname",
                "Email",
                "Primary Phone",
                "Secondary Phone",
                "Emergency Contact Given Name",
                "Emergency Contact Surname",
                "Emergency Contact Primary Phone",
                "Emergency Contact Relationship"
        );
    }

    private Stream<String> parseOne(Path path) throws IOException {
        Stream<String> lines = lines(path);
        VBSForm form = new VBSFormParser().map(new LineParser().parseLines(lines));
        if(!form.getChildren().isEmpty()) {
            Path completedLocation = Paths.get("./completed");
            completedLocation.toFile().mkdirs();
            int i=1;
            boolean moved = false;
            do {
                try {
                    Files.move(path, completedLocation.resolve(Integer.toString(i)+path.getFileName()));
                    moved = true;
                } catch (FileAlreadyExistsException e) {
                    i++;
                }
            } while(!moved);
        }
        return form.getChildren().stream().map(child -> map(child, form));
    }

    private String map(Child child, VBSForm form) {
        return toCSV(
                child.getGivenName(),
                child.getSurname(),
                child.getAge(),
                child.getGrade(),
                child.getNote(),
                form.getAddress().getStreet(),
                form.getAddress().getStreet2(),
                form.getAddress().getCity(),
                form.getAddress().getState(),
                form.getAddress().getRegion(),
                form.getAddress().getCountry(),
                form.getParent().getGivenName(),
                form.getParent().getSurname(),
                form.getParent().getEmail(),
                form.getParent().getPrimaryPhone(),
                form.getParent().getSecondaryPhone(),
                form.getEmergencyContact().getGivenName(),
                form.getEmergencyContact().getSurname(),
                form.getEmergencyContact().getPrimaryPhone(),
                form.getEmergencyContact().getRelationship()
        );
    }

    private String toCSV(Object... values) {
        return Stream.of(values)
                .map(v -> v == null ? "" : v.toString())
                .map(n->n.replace("'", "\\'"))
                .collect(joining(","));
    }

    private Stream<String> parseAll(Path path) throws IOException {
        return Files.list(path)
                .filter(p->!(p.toFile().getName().endsWith(".csv") || p.toFile().getName().endsWith(".numbers")))
                .filter(p->!p.toFile().isDirectory())
                .flatMap(p -> {
                    try {
                        return parseOne(p);
                    } catch (IOException e) {
                        throw new RuntimeException(p.toString(), e);
                    }
                });
    }

}
