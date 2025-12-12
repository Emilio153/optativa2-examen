package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Oferta;
import com.daw.persistence.repositories.OfertaRepository;
import com.daw.persistence.repositories.PizzaRepository;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private PizzaRepository pizzaRepository; // Para validar que la pizza existe

    public List<Oferta> findAll() {
        return this.ofertaRepository.findAll();
    }

    public Oferta create(Oferta oferta) {
        if (!this.pizzaRepository.existsById(oferta.getIdPizza())) {
            throw new RuntimeException("La pizza asociada no existe.");
        }

        // REGLA: Al crear, se marca activa por defecto
        oferta.setActiva(true);
        oferta.setId(0); // Asegurar creación

        // Desactivar las otras ofertas de esta misma pizza
        desactivarOtrasOfertas(oferta.getIdPizza(), 0); 

        return this.ofertaRepository.save(oferta);
    }

    public Oferta update(int id, Oferta oferta) {
        if (!this.ofertaRepository.existsById(id)) {
            throw new RuntimeException("Oferta no encontrada");
        }
        oferta.setId(id);
        
        // Si al actualizar la ponemos activa, desactivamos las demás
        if(oferta.isActiva()) {
            desactivarOtrasOfertas(oferta.getIdPizza(), id);
        }
        
        return this.ofertaRepository.save(oferta);
    }

    public void delete(int id) {
        this.ofertaRepository.deleteById(id);
    }

    // Ejercicio 2: Marcar activa
    public Oferta marcarActiva(int idOferta) {
        Oferta oferta = this.ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        oferta.setActiva(true);
        this.ofertaRepository.save(oferta);

        // Desactivar el resto de esa pizza
        desactivarOtrasOfertas(oferta.getIdPizza(), idOferta);

        return oferta;
    }

    // Método auxiliar privado
    private void desactivarOtrasOfertas(int idPizza, int idOfertaExcluida) {
        List<Oferta> ofertas = this.ofertaRepository.findByIdPizza(idPizza);
        for (Oferta of : ofertas) {
            if (of.getId() != idOfertaExcluida) {
                of.setActiva(false);
                this.ofertaRepository.save(of);
            }
        }
    }
}