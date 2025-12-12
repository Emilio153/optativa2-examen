package com.daw.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oferta")
@Getter
@Setter
@NoArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String nombre;

    @Column(columnDefinition = "BOOLEAN")
    private boolean activa;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private double descuento; // Porcentaje (ej: 10.0 para 10%) o cantidad fija, asumiremos porcentaje por lógica de examen

    @Column(name = "id_pizza")
    private int idPizza;

    @ManyToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Pizza pizza;
}
