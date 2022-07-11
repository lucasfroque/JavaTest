package br.com.cd2.sigabem.controllers;

import br.com.cd2.sigabem.dto.FreteDto;
import br.com.cd2.sigabem.models.Frete;

import br.com.cd2.sigabem.services.FreteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/frete")
public class FreteController {

    @Autowired
    private FreteService service;

    @Operation(summary = "Consulta o frete e salva no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Cep não encontrado", content = @Content(array = @ArraySchema(schema = @Schema(example = " ")))),
            @ApiResponse(responseCode = "400", description = "Requisição incorreta", content = @Content(array = @ArraySchema(schema = @Schema(example = " ")))),
            @ApiResponse(responseCode = "201", description = "Criado")
    })
    @PostMapping
    public ResponseEntity<Frete> insert(@RequestBody FreteDto obj){
        Frete response = service.create(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Lista todos os frete consultados")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public ResponseEntity<List<Frete>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}