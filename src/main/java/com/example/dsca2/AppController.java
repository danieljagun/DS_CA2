package com.example.dsca2;

import com.example.dsca2.repository.ActualEmission;
import com.example.dsca2.repository.User;
import com.example.dsca2.service.ActualEmissionService;
import com.example.dsca2.service.ParsingService;
import com.example.dsca2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emissions")
public class AppController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ParsingService parsingService;
    private final UserService userService;
    private final ActualEmissionService actualEmissionService;

    public AppController(ParsingService parsingService, UserService userService, ActualEmissionService actualEmissionService) {
        this.parsingService = parsingService;
        this.userService = userService;
        this.actualEmissionService = actualEmissionService;
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
    public ResponseEntity<User> updateUser(@PathVariable Long userID, User updatedUser) {
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


}
