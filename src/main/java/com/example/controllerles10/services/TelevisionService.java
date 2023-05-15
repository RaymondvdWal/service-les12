package com.example.controllerles10.services;

import com.example.controllerles10.dtos.TelevisionDto;
import com.example.controllerles10.dtos.TelevisionInputDto;
import com.example.controllerles10.model.Television;
import com.example.controllerles10.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepo;
    private TelevisionDto televisionDto;
    private TelevisionInputDto televisionInputDto;

    public TelevisionService(TelevisionRepository televisionRepo) {
        this.televisionRepo = televisionRepo;
    }

    List<TelevisionDto> televisionDtos = new ArrayList<>();

    public List<TelevisionDto> getAllTelevision() {

        List<Television> televisions = televisionRepo.findAll();

        for (Television television : televisions) {

            televisionDto = new TelevisionDto();

            televisionDto.setId(television.getId());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setType(television.getType());
            televisionDto.setScreenSize(television.getScreenSize());

            televisionDtos.add(televisionDto);
        }

        return televisionDtos;

    }

    public TelevisionDto getTelevision(Long id) {

        Television television = televisionRepo.getReferenceById(id);

            televisionDto = new TelevisionDto();

            assert television.id != null;
            televisionDto.setId(television.getId());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setType(television.getType());
            televisionDto.setScreenSize(television.getScreenSize());

            return televisionDto;

    }


    public Long createTelevision(TelevisionDto  televisionDto) {

        Television television = new Television();

        television.setBrand(televisionDto.brand);
        television.setPrice(televisionDto.price);
        television.setType(televisionDto.type);
        television.setScreenSize(televisionDto.screenSize);

        televisionRepo.save(television);

        return television.getId();
    }


    public void updateTelevision(Long id, TelevisionDto televisionDto) {

        Television television = televisionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Television not found with id " + id)
        );

        television.setBrand(televisionDto.getBrand());
        television.setPrice(televisionDto.getPrice());
        television.setType(televisionDto.getType());
        television.setScreenSize(televisionDto.getScreenSize());

        televisionRepo.save(television);
    }

    public void deleteTelevision(Long id) {
        Television television = televisionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Television not found with id " + id)
        );

        televisionRepo.delete(television);
    }

}
