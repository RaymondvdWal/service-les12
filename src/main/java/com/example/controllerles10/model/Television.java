package com.example.controllerles10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "televisions")
@NoArgsConstructor
public class Television {

        @Id
        @GeneratedValue
        public Long id;
        public String brand;
        public String type;
        public double price;
        public int screenSize;

        public Television(String brand, double price, String type, int screenSize) {
                this.brand = brand;
                this.price = price;
                this.type = type;
                this.screenSize = screenSize;
        }
}
