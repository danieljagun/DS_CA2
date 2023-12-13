package com.example.dsca2;

import com.example.dsca2.service.ParsingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emissions")
public class AppController {

    private final ParsingService parsingService;

    public AppController(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    @GetMapping("/parse-xml")
    public String parseXMLData() {
        parsingService.parseXMLData();
        return "XML data parsed and saved";
    }

    @GetMapping("/parse-json")
    public String parseJSONData() {
        parsingService.parseJSONData();
        return "JSON data parsed and saved";
    }
}
