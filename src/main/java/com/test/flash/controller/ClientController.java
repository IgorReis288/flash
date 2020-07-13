package com.test.flash.controller;

import com.test.flash.domain.Client;
import com.test.flash.repository.ClientRepository;
import com.test.flash.repository.PetRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/cliente")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PetRepository petRepository;

    @PostMapping("/cadastrar")
    @ApiOperation(value="Cadastra clientes na base")
    public ResponseEntity register(@Valid @RequestBody Client client){

        petRepository.saveAll(client.getPets());
        clientRepository.save(client);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }

    @PutMapping("/alterar")
    @ApiOperation(value="Atualiza os dados de um cliente existente na base")
    public ResponseEntity update(@Valid @RequestBody Client client) throws Exception {

        Client clientDB = clientRepository.findById(client.getId())
                                          .orElseThrow(() -> new Exception("O pet não foi encontrado na base"));
        clientDB.setName(client.getName());
        clientDB.setPets(client.getPets());
        clientRepository.save(clientDB);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @DeleteMapping("/deletar/{id}")
    @ApiOperation(value="deleta um cliente na base")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) throws Exception {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("O pet não foi encontrado na base"));
        clientRepository.delete(client);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @GetMapping("/visualizar/{id}")
    @ApiOperation(value="visualiza um cliente na base")
    public ResponseEntity view(@RequestParam(name = "id") Long id) throws Exception {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientRepository.findById(id)
                        .orElseThrow(() -> new Exception("O cliente não foi encontrado na base")));

    }

    @GetMapping("/listar")
    @ApiOperation(value="visualiza todos os clientes na base")
    public ResponseEntity viewList(){

       return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientRepository.findAll());
    }
}
