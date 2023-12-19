package com.example.dsca2;

import com.example.dsca2.repository.ActualEmission;
import com.example.dsca2.repository.PredictedEmission;
import com.example.dsca2.repository.User;
import com.example.dsca2.repository.UserCredentials;
import com.example.dsca2.service.ActualEmissionService;
import com.example.dsca2.service.ParsingService;
import com.example.dsca2.service.PredictedEmissionService;
import com.example.dsca2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emissions")
public class AppController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ParsingService parsingService;
    private final UserService userService;
    private final ActualEmissionService actualEmissionService;
    private final PredictedEmissionService predictedEmissionService;
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    public AppController(ParsingService parsingService, UserService userService, ActualEmissionService actualEmissionService, PredictedEmissionService predictedEmissionService) {
        this.parsingService = parsingService;
        this.userService = userService;
        this.actualEmissionService = actualEmissionService;
        this.predictedEmissionService = predictedEmissionService;
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

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Received request to create User: {}", user);
        User createdUser = userService.createUser(user);
        logger.info("Created user with ID: {}", createdUser.getUserID());
        return ResponseEntity.ok().body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        String name = credentials.getName();
        String password = credentials.getPassword();
        User user = userService.getUserByName(name);

        if (user != null && user.getPassword().equals(password)) {
            String token = generateToken();
            String message = "User logged in with userID - " + user.getUserID() + " and token - " + token;
            return ResponseEntity.ok().body(message);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user details, try again");
        }
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getUser(@PathVariable Long userID) {
        logger.info("Received request to get User with ID: {}", userID);
        User user = userService.getUser(userID);
        if (user != null) {
            logger.info("Retrieved user with ID: {}", user.getUserID());
            return ResponseEntity.ok().body(user);
        } else {
            logger.info("User with ID {} not found", userID);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/users/update/{userID}")
    public ResponseEntity<User> updateUser(@PathVariable Long userID, @RequestBody User updatedUser) {
        logger.info("Received request to update User with ID: {}", userID);
        User user = userService.updateUser(userID, updatedUser);
        if (user != null) {
            logger.info("Updated user with ID: {}", user.getUserID());
            return ResponseEntity.ok().body(user);
        } else {
            logger.info("User with ID {} not found", userID);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/delete/{userID}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userID) {
        logger.info("Received request to delete User with ID: {}", userID);
        userService.deleteUser(userID);
        logger.info("Deleted user with ID: {}", userID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/actual-emissions")
    public ResponseEntity<ActualEmission> createActualEmission(@RequestBody ActualEmission actualEmission) {
        logger.info("Received request to create Actual Emission: {}", actualEmission);
        Long emissionID = actualEmissionService.createActualEmission(actualEmission);
        logger.info("Created actual emission with ID: {}", emissionID);
        return ResponseEntity.ok().body(actualEmission);
    }

    @GetMapping("/actual-emissions/{emissionID}")
    public ResponseEntity<ActualEmission> getActualEmission(@PathVariable Long emissionID) {
        logger.info("Received request to get Actual Emission with ID: {}", emissionID);
        ActualEmission actualEmission = actualEmissionService.getActualEmission(emissionID);
        if (actualEmission != null) {
            logger.info("Retrieved actual emission with ID: {}", actualEmission.getEmissionID());
            return ResponseEntity.ok().body(actualEmission);
        } else {
            logger.info("Actual Emission with ID {} not found", emissionID);
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/actual-emissions/update/{emissionID}")
    public ResponseEntity<ActualEmission> updateActualEmission(@PathVariable Long emissionID, @RequestBody ActualEmission updatedActual) {
        logger.info("Received request to update Actual Emission with ID: {}", emissionID);
        ActualEmission actualEmission = actualEmissionService.updateActualEmission(emissionID, updatedActual);
        if (actualEmission != null) {
            logger.info("Updated actual emission with ID: {}", actualEmission.getEmissionID());
            return ResponseEntity.ok().body(actualEmission);
        } else {
            logger.info("Actual Emission with ID {} not found", emissionID);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/actual-emissions/delete/{emissionID}")
    public ResponseEntity<Void> deleteActualEmission(@PathVariable Long emissionID) {
        logger.info("Received request to delete Actual Emission with ID: {}", emissionID);
        actualEmissionService.deleteActualEmission(emissionID);
        logger.info("Deleted actual emission with ID: {}", emissionID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/actual-emissions/list")
    public ResponseEntity<List<ActualEmission>> getAllActualEmissions() {
        logger.info("Received request to get all Actual Emissions");
        List<ActualEmission> actualEmissions = actualEmissionService.getAllActualEmissions();

        if (!actualEmissions.isEmpty()) {
            logger.info("Retrieved {} actual emissions", actualEmissions.size());
            return ResponseEntity.ok().body(actualEmissions);
        } else {
            logger.info("No actual emissions found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/actual-emissions/category/{category}")
    public ResponseEntity<List<ActualEmission>> getActualEmissionsByCategory(@PathVariable String category) {
        List<ActualEmission> emissionsByCategory = actualEmissionService.getActualEmissionsByCategory(category);
        if (!emissionsByCategory.isEmpty()) {
            logger.info("Retrieved {} actual emissions for category: {}", emissionsByCategory.size(), category);
            return ResponseEntity.ok().body(emissionsByCategory);
        } else {
            logger.info("No actual emissions found for category: {}", category);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/predicted-emissions")
    public ResponseEntity<PredictedEmission> createPredictedEmission(@RequestBody PredictedEmission predictedEmission) {
        logger.info("Received request to create Predicted Emission: {}", predictedEmission);
        Long emissionID = predictedEmissionService.createPredictedEmission(predictedEmission);
        logger.info("Created predicted emission with ID: {}", emissionID);
        return ResponseEntity.ok().body(predictedEmission);
    }

    @GetMapping("/predicted-emissions/{emissionID}")
    public ResponseEntity<PredictedEmission> getPredictedEmission(@PathVariable Long emissionID) {
        logger.info("Received request to get Predicted Emission with ID: {}", emissionID);
        PredictedEmission predictedEmission = predictedEmissionService.getPredictedEmission(emissionID);
        if (predictedEmission != null) {
            logger.info("Retrieved predicted emission with ID: {}", predictedEmission.getEmissionID());
            return ResponseEntity.ok().body(predictedEmission);
        } else {
            logger.info("Predicted Emission with ID {} not found", emissionID);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/predicted-emissions/update/{emissionID}")
    public ResponseEntity<PredictedEmission> updatePredictedEmission(@PathVariable Long emissionID, @RequestBody PredictedEmission updatedPredicted) {
        logger.info("Received request to update Predicted Emission with ID: {}", emissionID);
        PredictedEmission predictedEmission = predictedEmissionService.updatePredictedEmission(emissionID, updatedPredicted);
        if (predictedEmission != null) {
            logger.info("Updated predicted emission with ID: {}", predictedEmission.getEmissionID());
            return ResponseEntity.ok().body(predictedEmission);
        } else {
            logger.info("Predicted Emission with ID {} not found", emissionID);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/predicted-emissions/delete/{emissionID}")
    public ResponseEntity<Void> deletePredictedEmission(@PathVariable Long emissionID) {
        logger.info("Received request to delete Predicted Emission with ID: {}", emissionID);
        predictedEmissionService.deletePredictedEmission(emissionID);
        logger.info("Deleted predicted emission with ID: {}", emissionID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/predicted-emissions/list")
    public ResponseEntity<List<PredictedEmission>> getAllPredictedEmissions() {
        logger.info("Received request to get all Predicted Emissions");
        List<PredictedEmission> predictedEmissions = predictedEmissionService.getAllPredictedEmissions();

        if (!predictedEmissions.isEmpty()) {
            logger.info("Retrieved {} actual emissions", predictedEmissions.size());
            return ResponseEntity.ok().body(predictedEmissions);
        } else {
            logger.info("No actual emissions found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/predicted-emissions/category/{category}")
    public ResponseEntity<List<PredictedEmission>> getPredictedEmissionsByCategory(@PathVariable String category) {
        List<PredictedEmission> emissionsByCategory = predictedEmissionService.getPredictedEmissionsByCategory(category);
        if (!emissionsByCategory.isEmpty()) {
            logger.info("Retrieved {} predicted emissions for category: {}", emissionsByCategory.size(), category);
            return ResponseEntity.ok().body(emissionsByCategory);
        } else {
            logger.info("No predicted emissions found for category: {}", category);
            return ResponseEntity.notFound().build();
        }
    }
}
