package com.test.flash.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    @ApiModelProperty(value= "identificador do cliente", example = "1")
    private long id;

    @Column(name = "name")
    @NotNull(message = "O cliente não pode ser criado sem um nome")
    @ApiModelProperty(value= "Nome do cliente", example = "Igor Reis")
    private String name;

    @OneToMany
    @JoinColumn(name = "pet_fk")
    @ApiModelProperty(value= "Lista de pets")
    private List<Pet> pets;

}
