package com.example.dsca2;

import com.example.dsca2.repository.EmissionEntity;
import com.example.dsca2.repository.EmissionRepository;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Component
public class ParseEmission {

    private final EmissionRepository emissionRepository;

    public ParseEmission(EmissionRepository emissionRepository) {
        this.emissionRepository = emissionRepository;
    }

    public void parseEmissionData() {
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

                if (!valueStr.isEmpty() && !valueStr.equals("") && !valueStr.equals("0")) {
                    if (year == 2023 && scenario.equals("WEM")) {

                        EmissionEntity emissionEntity = new EmissionEntity();
                        emissionEntity.setCategory(category);
                        emissionEntity.setYear(year);
                        emissionEntity.setScenario(scenario);
                        emissionEntity.setGasUnits(gasUnits);
                        emissionEntity.setValue(valueStr);

                        emissionRepository.save(emissionEntity);

                        validEmissionCount++;
                    }
                }
            }

            System.out.println("Total valid emissions: " + validEmissionCount);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
