package com.github.averyregier.vbs.parser;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by avery on 7/14/15.
 */
public class LineParser {

    public List<FormField> parse(String toParse) {
        ArrayList<FormField> result = new ArrayList<>();

        for(String line: splitLines(toParse)) {
            parseLine(result, line);
        }
        return result;
    }

    public void parseLine(ArrayList<FormField> result, String line) {
        if(line.endsWith("=20")) {
            line = line.substring(0, line.length()-3);
        }
        line = line+" ";
        String[] split = line.split(":\\W");

        if(split.length > 1) {
            String key = combine(split, ": ", 0, split.length-1);
            String value = split[split.length-1];
            value = value.replaceAll("\\W", " ");
            result.add(new FormField(key, value.trim()));
        } else {
            System.err.println(line);
        }
    }

    private String combine(String[] split, String s, int start, int count) {
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<start+count; i++){
            sb.append(split[i]);
            sb.append(s);
        }
        return sb.toString();
    }

    private String[] splitLines(String toParse) {
        return toParse.split("\n");
    }

    public static void main(String... values) throws IOException {
        try (Stream<String> lines = Files.lines(FileSystems.getDefault().getPath(values[0]))) {
            LineParser parser = new LineParser();
            ArrayList<FormField> result = parser.parseLines(lines);

            result.forEach(System.out::println);
        }
    }

    public ArrayList<FormField> parseLines(Stream<String> lines) {
        ArrayList<FormField> result = new ArrayList<>();
        try {
            lines.forEach(line -> parseLine(result, line));
        } catch(Exception e) {
        }
        return result;
    }
}
