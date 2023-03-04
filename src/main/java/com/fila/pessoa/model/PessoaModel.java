package com.fila.pessoa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.Link;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_PESSOAS")
public class PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pessoaId;
    @Column(nullable = false, length = 200)
    private String nome;
    @Column(nullable = false, length = 3)
    private String idade;
    private String posicaoNaFila;


}
