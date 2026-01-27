package com.javanauta.aprendendospring.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "complemento", nullable = true)
    private String complemento;

    @Column(name = "numero", nullable = true)
    private String numero;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "sigla_estado", length = 2, nullable = false)
    private String sigla_estado;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;
}
