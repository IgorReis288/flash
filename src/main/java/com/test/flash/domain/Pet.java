package com.test.flash.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    @ApiModelProperty(value= "Identificador do pet", example = "1", allowEmptyValue = true)
    private long id;

    @Column(name = "type")
    @NotNull(message = "O tipo do pet não foi informado")
    @ApiModelProperty(value= "Tipo do pet", example = "cão")
    private String type;

    @Column(name = "breed")
    @NotNull(message = "O tipo do pet não foi informado")
    @ApiModelProperty(value= "Raça do pet", example = "pug")
    private String breed;

    @Column(name = "name")
    @NotNull(message = "O pet não pode ser criado sem um nome")
    @ApiModelProperty(value= "Nome do pet", example = "luna")
    private String name;
}
