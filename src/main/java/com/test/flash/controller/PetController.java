package com.test.flash.controller;

import com.test.flash.domain.Pet;
import com.test.flash.repository.PetRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pet")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @PostMapping("/cadastrar")
    @ApiOperation(value="Cadastra um pet na base")
    public ResponseEntity register(@Valid @RequestBody Pet pet){

        petRepository.save(pet);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }

    @PutMapping("/alterar")
    @ApiOperation(value="Atualiza os dados de um pet existente na base")
    public ResponseEntity update(@Valid @RequestBody Pet pet) throws Exception {

        Pet PetDB = petRepository.findById(pet.getId())
                                 .orElseThrow(() -> new Exception("O pet não foi encontrado na base"));
        PetDB.setName(pet.getName());
        PetDB.setType(pet.getType());
        PetDB.setBreed(pet.getBreed());
        petRepository.save(PetDB);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value="Deleta os dados de um pet existente na base")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) throws Exception {

        Pet pet = petRepository.findById(id).orElseThrow(() -> new Exception("O pet não foi encontrado na base"));
        petRepository.delete(pet);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @GetMapping("/visualizar/{id}")
    @ApiOperation(value="Visualiza os dados de um pet existente na base")
    public ResponseEntity view(@RequestParam(name = "id") Long id) throws Exception {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petRepository.findById(id).orElseThrow(() -> new Exception("O pet não foi encontrado na base")));
    }

    @GetMapping("/listar")
    @ApiOperation(value="Lista todos os pets existentes na base")
    public ResponseEntity viewList(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(petRepository.findAll());
    }
}
