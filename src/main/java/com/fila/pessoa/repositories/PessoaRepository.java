package com.fila.pessoa.repositories;

import com.fila.pessoa.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaModel, UUID> , JpaSpecificationExecutor<PessoaModel> {
    boolean existsByNome(String nome);
}
