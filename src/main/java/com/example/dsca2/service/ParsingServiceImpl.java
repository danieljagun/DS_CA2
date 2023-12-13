package com.example.dsca2.service;

import com.example.dsca2.repository.ActualEmissionEntity;
import com.example.dsca2.repository.ActualEmissionRepository;
import com.example.dsca2.repository.PredictedEmissionEntity;
import com.example.dsca2.repository.PredictedEmissionRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Service
public class ParsingServiceImpl implements ParsingService {

    private final PredictedEmissionRepository predictedEmissionRepository;
    private final ActualEmissionRepository actualEmissionRepository;

    public ParsingServiceImpl(PredictedEmissionRepository predictedEmissionRepository, ActualEmissionRepository actualEmissionRepository) {
        this.predictedEmissionRepository = predictedEmissionRepository;
        this.actualEmissionRepository = actualEmissionRepository;
    }

    @Override
    public void parseXMLData() {
        try {
            File xmlFile = new File("emission.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList rows = doc.getElementsByTagName("Row");
            int validEmissionCount = 0;

            for (int i = 0; i < rows.getLength(); i++) {
                Element row = (Element) rows.item(i);

                String category = row.getElementsByTagName("Category__1_3").item(0).getTextContent();
                int year = Integer.parseInt(row.getElementsByTagName("Year").item(0).getTextContent());
                String scenario = row.getElementsByTagName("Scenario").item(0).getTextContent();
                String gasUnits = row.getElementsByTagName("Gas___Units").item(0).getTextContent();
                String valueStr = row.getElementsByTagName("Value").item(0).getTextContent();

                if (!valueStr.isEmpty() && !valueStr.equals("0")) {
                    if (year == 2023 && scenario.equals("WEM")) {

                        PredictedEmissionEntity predictedEmissionEntity = new PredictedEmissionEntity();
                        predictedEmissionEntity.setCategory(category);
                        predictedEmissionEntity.setYear(year);
                        predictedEmissionEntity.setScenario(scenario);
                        predictedEmissionEntity.setGasUnits(gasUnits);
                        predictedEmissionEntity.setValue(valueStr);

                        predictedEmissionRepository.save(predictedEmissionEntity);

                        validEmissionCount++;
                    }
                }
            }

            System.out.println("Total valid emissions: " + validEmissionCount);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseJSONData() {
        try {
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

                    // Assuming value is always a Long or Double
                    String valueStr = String.valueOf(value);

                    // Save data to the database
                    ActualEmissionEntity actualEmissionEntity = new ActualEmissionEntity();
                    actualEmissionEntity.setCategory(category);
                    actualEmissionEntity.setGasUnits(gasUnits);
                    actualEmissionEntity.setValue(valueStr);

                    actualEmissionRepository.save(actualEmissionEntity);

                }
            } else {
                System.out.println("File doesn't contain 'Emissions' data.");
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }
}

