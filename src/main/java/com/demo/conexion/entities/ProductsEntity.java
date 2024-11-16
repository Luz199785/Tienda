package com.demo.conexion.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class ProductsEntity {
    @Id
    private UUID id;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    @PrePersist
    public void generateUUID(){
        if (id == null){
            id = UUID.randomUUID();
        }
    }
}
