package com.daw.services.dto;

import com.daw.persistence.entities.Oferta;
import com.daw.persistence.entities.Pizza;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaConOfertaDTO {
    private Pizza pizza;
    private Oferta ofertaActiva; // Puede ser null si no hay oferta

    public PizzaConOfertaDTO(Pizza pizza, Oferta ofertaActiva) {
        this.pizza = pizza;
        this.ofertaActiva = ofertaActiva;
    }
}