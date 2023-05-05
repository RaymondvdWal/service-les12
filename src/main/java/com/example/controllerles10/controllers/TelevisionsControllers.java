package com.example.controllerles10.controllers;

import com.example.controllerles10.exceptions.RecordNotFoundException;
import com.example.controllerles10.exceptions.ToManyCharException;
import com.example.controllerles10.model.Television;
import com.example.controllerles10.repository.TelevisionRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TelevisionsControllers {

    private final TelevisionRepository televisionRepository;

    public TelevisionsControllers(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    /*private final List<Television> televisions = new ArrayList<>();*/

    @GetMapping("/televisions")
    public List<Television> getAllTelevisions() {
        return (List<Television>) televisionRepository.findAll();
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable Long id) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("This "+ id +" doesn't exist");
        }
           Television television = optionalTelevision.get();
           return ResponseEntity.ok().body(television);
    }

    @PostMapping("/addTelevision")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) throws ToManyCharException {
        if (television.brand.length() > 20) {
            throw new ToManyCharException("Mag niet langer dan 20 letters zijn");
        }
        televisionRepository.save(television);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(television.getId()).toUri();
        return ResponseEntity.created(location).body(television);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable(value = "id") Long televisionId,
        @Valid @RequestBody Television televisionDetails) throws RecordNotFoundException {
            Television television = televisionRepository.findById(televisionId).orElseThrow(()
            -> new RecordNotFoundException("Television not found for this id :: "+televisionId));

            television.setType(televisionDetails.getType());
            television.setBrand(televisionDetails.getBrand());
            television.setPrice(televisionDetails.getPrice());
            television.setScreenSize(televisionDetails.getScreenSize());
            final Television updateTelevision = televisionRepository.save(television);
            return ResponseEntity.ok(updateTelevision);
    }

    @DeleteMapping("/televisions/{id}")
    public Map<String, Boolean> deleteTelevision(@PathVariable(value = "id") Long televisionId)
        throws RecordNotFoundException{
            Television television = televisionRepository.findById(televisionId).orElseThrow(()
            -> new RecordNotFoundException("Television not found for this id :: "+televisionId));

            televisionRepository.delete(television);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
    }
}
