package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw.persistence.entities.Oferta;
import com.daw.services.OfertaService;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping
    public ResponseEntity<List<Oferta>> list() {
        return ResponseEntity.ok(this.ofertaService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Oferta oferta) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.ofertaService.create(oferta));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Oferta oferta) {
        try {
            return ResponseEntity.ok(this.ofertaService.update(id, oferta));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        this.ofertaService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Ejercicio 2: Endpoint específico para activar
    @PutMapping("/{id}/activar")
    public ResponseEntity<?> activarOferta(@PathVariable int id) {
        try {
            return ResponseEntity.ok(this.ofertaService.marcarActiva(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}