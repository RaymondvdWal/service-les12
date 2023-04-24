package com.example.controllerles10.controllers;

import com.example.controllerles10.exceptions.RecordNotFoundException;
import com.example.controllerles10.exceptions.ToManyCharException;
import com.example.controllerles10.model.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TelevisionsControllers {

    private final List<Television> televisions = new ArrayList<>();

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions(){
        return new ResponseEntity<>(televisions, HttpStatus.OK);
    }
    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable int id) throws RecordNotFoundException {
        if (id >= 0 && id < televisions.size()){
            return new ResponseEntity<>(televisions.get(id), HttpStatus.FOUND);
        }
        else {
            throw new RecordNotFoundException("This id doesn't exist");
        }
    }

    @PostMapping("/addTelevision")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) throws ToManyCharException {
        if (television.brand.length() > 20) {
            throw new ToManyCharException("Mag niet langer dan 20 letters zijn");
        }
        televisions.add(television);
        return new ResponseEntity<>(television, HttpStatus.CREATED);

    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Television> updateTelevision(@RequestBody Television television, @PathVariable int id) {
        if (id >= 0 && id < televisions.size()) {
            televisions.set(id, television);
            return new ResponseEntity<>(television, HttpStatus.OK);
        }
        else {
            // error situation
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable int id) throws IndexOutOfBoundsException{
        if (id >= 0 && id < televisions.size()){
            televisions.remove(televisions.get(id));
            return new ResponseEntity<>(televisions.get(id), HttpStatus.ACCEPTED);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }


}
