package com.michaelfotiadis.csv.editor;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvProcessor {

    private final String path;
    private final String exportPath;

    public CsvProcessor(final String path, final String exportPath) {

        final File file = new File(path);
        if (!file.exists()) {
            throw new IllegalStateException("File " + path + " does not exist");
        }
        this.path=path;
        this.exportPath = exportPath;

        System.out.println("Using file " + file.getAbsolutePath());

    }

    public void process() {
        final List<String[]> content = new ArrayList<>();
        try {
            content.addAll(readFile(path));
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }

        final List<String[]> optimisedContent = new ArrayList<>();

        final DecimalFormat df = new DecimalFormat("#.000000");

        for (final String[] entry : content) {

            if (entry.length != 4) {
                throw new IllegalStateException("Invalid entry in array");
            }

            if (!entry[0].equals("id")) {

                final String[] optimisedEntry = new String[3];

                optimisedEntry[0] = entry[1].replaceAll(" ", "");


                optimisedEntry[1] = df.format(Double.valueOf(entry[2]));
                optimisedEntry[2] = df.format(Double.valueOf(entry[3]));


                optimisedContent.add(optimisedEntry);
            }
        }

        try {
            writeFile(optimisedContent, exportPath);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readFile(final String path) throws IOException {

        final Reader reader = new java.io.FileReader(path);

        final CSVReader csvReader = new CSVReader(reader);

        return csvReader.readAll();

    }

    private static void writeFile(final List<String[]> content, final String exportPath) throws IOException {

        final CSVWriter writer = new CSVWriter(new FileWriter(exportPath), ',');
        // feed in your array (or convert your data to an array)

        writer.writeAll(content);
        writer.close();


    }

}
