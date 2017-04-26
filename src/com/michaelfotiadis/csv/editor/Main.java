package com.michaelfotiadis.csv.editor;

public final class Main {

    private static final String PATH = "res/ukpostcodes.csv";
    private static final String EXPORT_PATH = "optimised_postcodes.csv";

    private Main() {
        // NOOP
    }

    public static void main(final String[] args) {

        System.out.println("Started");

        final CsvProcessor csvProcessor = new CsvProcessor(PATH, EXPORT_PATH);

        csvProcessor.process();

    }


}
