package br.com.cd2.sigabem.controllers;

import br.com.cd2.sigabem.dto.FreteDto;
import br.com.cd2.sigabem.models.Frete;

import br.com.cd2.sigabem.services.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/frete")
public class FreteController {

    @Autowired
    private FreteService service;

    @PostMapping
    public ResponseEntity<Frete> insert(@RequestBody FreteDto obj){
        Frete response = service.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
    @GetMapping
    public ResponseEntity<List<Frete>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}