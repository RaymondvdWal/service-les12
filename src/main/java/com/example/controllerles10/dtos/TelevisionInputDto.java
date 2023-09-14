package com.example.controllerles10.dtos;

import com.example.controllerles10.model.Television;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelevisionInputDto {

        public Long id;
        @NotBlank
        public String brand;
        @NotBlank
        public String type;
        @Min(value = 25, message = "The price must be at least €25,-")
        public double price;
        @NotNull
        public int screenSize;


    public Television toTelevision(Television television) {

        television.setId(this.id);
        television.setBrand(this.brand);
        television.setType(this.type);
        television.setPrice(this.price);
        television.setScreenSize(this.screenSize);
        return television;
    }
}
