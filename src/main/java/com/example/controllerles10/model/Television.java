package com.example.controllerles10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "televisions")
public class Television {

        @Id
        @GeneratedValue
        public Long id;
        public String brand;
        public String type;
        public double price;
        public int screenSize;

}
