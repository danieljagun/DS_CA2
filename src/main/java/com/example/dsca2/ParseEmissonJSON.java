package com.example.dsca2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseEmissonJSON {

    public ParseEmissonJSON() throws FileNotFoundException, ParseException {
        File file = new File("GreenhouseGasEmissions2023.json");
        Scanner scanner = new Scanner(file);

        StringBuilder jsonString = new StringBuilder();
        while (scanner.hasNext()) {
            jsonString.append(scanner.nextLine());
        }

        // What that "file" looks like as a String
        System.out.println("JSON from file as a String:");
        System.out.println(jsonString);

        // Parse the JSON string
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonString.toString());
        System.out.println("\nParsed JSON:");
        System.out.println(jsonObject);

        if (jsonObject.containsKey("Emissions")) {
            JSONArray emissionsArray = (JSONArray) jsonObject.get("Emissions");

            // Iterate through emissions data
            for (Object emissionObj : emissionsArray) {
                JSONObject emission = (JSONObject) emissionObj;

                String category = (String) emission.get("Category");
                String gasUnits = (String) emission.get("Gas Units");
                Number value = (Number) emission.get("Value");

                // Print values to the console
                System.out.println("\nCategory: " + category);
                System.out.println("Gas Units: " + gasUnits);
                System.out.println("Value: " + value);
            }
        } else {
            System.out.println("File doesn't contain 'Emissions' data.");
        }
    }

    public static void main(String[] args) {
        try {
            new ParseEmissonJSON();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
